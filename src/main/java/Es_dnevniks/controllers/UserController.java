package Es_dnevniks.controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.UserEntity;
import Es_dnevniks.entities.dto.UserDTO;
import Es_dnevniks.entities.dto.UserEntityDTO;
import Es_dnevniks.exception.FileErrors;
import Es_dnevniks.repository.RoleRepository;
import Es_dnevniks.repository.UserRepository;
import Es_dnevniks.services.TeacherService;
import Es_dnevniks.services.UserService;
import Es_dnevniks.utils.Encryption;
import Es_dnevniks.utils.UserCustomValidator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/es_dnevnik/admin")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@Autowired
	TeacherService teacherService;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserCustomValidator userValidator;
	@Autowired
	private SecretKey secretKey;
	@Value("${spring.security.token-duration}")
	private Integer tokenDuration;
	
	@InitBinder
	protected void initBinder(final WebDataBinder binder)
	{
	binder.addValidators(userValidator);
	}
	
	private String getJWTToken(UserEntity userEntity) {
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
		.commaSeparatedStringToAuthorityList(userEntity.getRole().getName());
		String token = Jwts.builder().setId("softtekJWT").setSubject(userEntity.getEmail())
		.claim("authorities", grantedAuthorities.stream()
		.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + this.tokenDuration))
		.signWith(this.secretKey).compact();
		return "Bearer " + token;
		}

	@RequestMapping(method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> add(@Valid @RequestBody UserEntityDTO user,BindingResult result) {
		try {
			if(result.hasErrors()) {
				FileErrors.appendToFile(createErrorMessage(result));
				return new ResponseEntity<>(createErrorMessage(result),HttpStatus.BAD_REQUEST);
			}else {
				userValidator.validate(user, result);
			}
			return ResponseEntity.status(HttpStatus.OK).body(userService.addUser(user));
		}catch (RESTError e) {
			FileErrors.appendToFile(e.getMessage());
			return new ResponseEntity<>(createErrorMessage(result),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestParam("email") String email, @RequestParam("password")
	String pwd) {
		UserEntity userEntity = userRepository.findByEmail(email).get();
		if (userEntity != null && Encryption.validatePassword(pwd, userEntity.getPassword())) {
			String token = getJWTToken(userEntity);
			UserDTO user = new UserDTO();
			user.setUser(email);
			user.setToken(token);
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Wrong credentials", HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(path = "/findByEmail/{email}", method = RequestMethod.GET)
	public ResponseEntity<?> login(@PathVariable String email){
		UserEntity user = userRepository.findByEmail(email).get();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/roles", method = RequestMethod.GET)
	public ResponseEntity<?> findAllRoles(){
		return new ResponseEntity<>(roleRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/users", method = RequestMethod.GET)
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> listUsers() {
		return new ResponseEntity<List<UserEntity>>((List<UserEntity>) userRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(path = "/family", method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> family(@RequestParam Integer parentId, @RequestParam Integer studentId) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.insertFamily(parentId, studentId));
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/studentSubject")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> adminGivesSubjectToStudent(@RequestParam Integer studentId, @RequestParam Integer subjectId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.adminGivesSubjectToStudent(studentId, subjectId));
		}catch(RESTError e){
			FileErrors.appendToFile(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/teacherSubject")
	@Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
	public ResponseEntity<?> adminGivesSubjectToTeacher(@RequestParam Integer teacherId, @RequestParam Integer subjectId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.adminGivesSubjectToTeacher(teacherId, subjectId));
		}catch(RESTError e){
			FileErrors.appendToFile(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/teacherSubjectDelete")
	@Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
	public ResponseEntity<?> adminRemovesSubjectFromTeacher(@RequestParam Integer teacherId, @RequestParam Integer subjectId) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.adminRemovesSubjectFromTeacher(teacherId, subjectId));
	
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/classToStudent")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> adminGivesClassToStudent(@RequestParam Integer classId, @RequestParam Integer studentId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.adminGivesClassToStudent(classId, studentId));
		}catch(RESTError e){
			FileErrors.appendToFile(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/classToTeacher")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> adminGivesClassToTeacher(@RequestParam Integer classId, @RequestParam Integer teacherId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.adminGivesClasstoTeacher(classId, teacherId));
		}catch(RESTError e){
			FileErrors.appendToFile(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> modifyUser(@PathVariable Integer id,@Valid @RequestBody UserEntityDTO user,BindingResult result)  {
		try {
			if(result.hasErrors()) {
				FileErrors.appendToFile(createErrorMessage(result));
				return new ResponseEntity<>(createErrorMessage(result),HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<>(userService.modify(id, user), HttpStatus.OK);

		} catch (RESTError e) {
			FileErrors.appendToFile(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
		
		@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
		@Secured("ROLE_ADMIN")
		public ResponseEntity<?> removeUser(@PathVariable Integer id)  {
			try {
				return ResponseEntity.status(HttpStatus.OK).body(userService.delete(id));
			} catch (RESTError e) {
				FileErrors.appendToFile(e.getMessage());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}

		}
		
		
		@RequestMapping(method = RequestMethod.GET, value = "/getMarks/{id}")
		@Secured("ROLE_ADMIN")
		public ResponseEntity<?> findMarksByStudents(@PathVariable Integer id) throws RESTError {
			return ResponseEntity.status(HttpStatus.OK).body(teacherService.teacherMarks(id));
		}
		
		@RequestMapping(method = RequestMethod.GET, value = "/{id}")
		public ResponseEntity<?> findUserById(@PathVariable Integer id)  {
			return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
		}

}

