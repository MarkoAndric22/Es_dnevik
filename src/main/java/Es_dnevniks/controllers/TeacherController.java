package Es_dnevniks.controllers;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
import Es_dnevniks.entities.TeacherEntity;
import Es_dnevniks.entities.UserEntity;
import Es_dnevniks.entities.dto.UserEntityDTO;
import Es_dnevniks.exception.FileErrors;
import Es_dnevniks.repository.TeacherRepository;
import Es_dnevniks.repository.UserRepository;
import Es_dnevniks.services.TeacherService;
import Es_dnevniks.utils.UserCustomValidator;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/es_dnevnik/teacher")
public class TeacherController {
	
	@Autowired
	TeacherService teacherService;
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserCustomValidator userValidator;
	@Autowired
	TeacherRepository teacherRepository;
	
	@InitBinder
	protected void initBinder(final WebDataBinder binder)
	{
	binder.addValidators(userValidator);
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> add(@Valid@RequestBody UserEntityDTO teacheres,BindingResult result) {
		try {
			if(result.hasErrors()) {
				FileErrors.appendToFile(createErrorMessage(result));
				return new ResponseEntity<>(createErrorMessage(result),HttpStatus.BAD_REQUEST);
			}else {
				userValidator.validate(teacheres, result);
			}
			return ResponseEntity.status(HttpStatus.OK).body(teacherService.addTeacher(teacheres));
		}catch (RESTError e) {
			FileErrors.appendToFile(e.getMessage());
			return new ResponseEntity<>(createErrorMessage(result),HttpStatus.BAD_REQUEST);
		}
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST, value = "/teacherEvaluatesStudent")
	@Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
	public ResponseEntity<?> teacherEvaluatesStudent(HttpServletRequest request, @RequestParam Integer studentId, @RequestParam Integer markId, @RequestParam Integer subjectId) throws Exception {
		try {
			String email = request.getUserPrincipal().getName();
			UserEntity teacher = userRepository.findByEmail(email).get();
			return ResponseEntity.status(HttpStatus.OK).body(teacherService.teacherEvaluatesStudent(teacher.getId(), studentId, markId, subjectId));
		}catch (RESTError e) {
			FileErrors.appendToFile(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> modifyTeacher(@PathVariable Integer id,@Valid @RequestBody UserEntityDTO teacher,BindingResult result)  {
		try {
			if(result.hasErrors()) {
				FileErrors.appendToFile(createErrorMessage(result));
				return new ResponseEntity<>(createErrorMessage(result),HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<>(teacherService.modify(id, teacher), HttpStatus.OK);

		} catch (RESTError e) {
			FileErrors.appendToFile(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> removeTeacher(@PathVariable Integer id)  {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(teacherService.delete(id));
		} catch (RESTError e) {
			FileErrors.appendToFile(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.GET, value = "/getMarks")
	@Secured("ROLE_TEACHER")
	public ResponseEntity<?> findMarksByStudents(HttpServletRequest request) throws RESTError {
		String email = request.getUserPrincipal().getName();
		UserEntity user = userRepository.findByEmail(email).get();
		return ResponseEntity.status(HttpStatus.OK).body(teacherService.teacherMarks(user.getId()));
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.PUT, value = "/modifyMarks/{id}")
	@Secured({"ROLE_ADMIN","ROLE_TEACHER"})
	public ResponseEntity<?> modifyMarks(@PathVariable Integer id, @RequestParam Integer mark) throws RESTError {
		return ResponseEntity.status(HttpStatus.OK).body(teacherService.modifyMarks(id, mark));
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteMark/{id}")
	@Secured({"ROLE_ADMIN","ROLE_TEACHER"})
	public ResponseEntity<?> deleteMark(@PathVariable Integer id) throws RESTError {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(teacherService.deleteMark(id));
		}catch (RESTError e) {
			FileErrors.appendToFile(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.GET, value = "/search/{teacher_id}")
	@Secured({"ROLE_ADMIN","ROLE_TEACHER"})
	public ResponseEntity<?> search(@PathVariable Integer teacher_id, @RequestParam String subject) throws RESTError {
		return ResponseEntity.status(HttpStatus.OK).body(teacherService.search(teacher_id, subject));
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.GET,value = "by-subject/{subjectId}")
	public ResponseEntity<?> findTeachersBySubject(@PathVariable Integer subjectId){
		return ResponseEntity.status(HttpStatus.OK).body(teacherService.teachersBySubject(subjectId));
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@Secured({"ROLE_ADMIN","ROLE_TEACHER"})
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<TeacherEntity> getAllTeachers() {
	    Iterable<TeacherEntity> teachers = teacherRepository.findAll();
	    ArrayList<TeacherEntity> teacherList = new ArrayList<>();
	    for (TeacherEntity teacher : teachers) {
	        teacherList.add(teacher);
	    }
	    return teacherList;
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.GET,value="/{id}")
	public ResponseEntity<TeacherEntity> getTeacherById(@PathVariable Integer id) {
	    try {
	        Optional<TeacherEntity> teacherOptional = teacherRepository.findById(id);
	        if (teacherOptional.isPresent()) {
	            TeacherEntity teacher = teacherOptional.get();
	            return ResponseEntity.ok(teacher);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}


}
