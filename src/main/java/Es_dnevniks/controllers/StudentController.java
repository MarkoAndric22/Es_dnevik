package Es_dnevniks.controllers;


import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RestController;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.dto.StudentAddDto;
import Es_dnevniks.entities.dto.UserEntityDTO;
import Es_dnevniks.exception.FileErrors;
import Es_dnevniks.services.StudentService;
import Es_dnevniks.utils.StudentCustomValidator;
import Es_dnevniks.utils.UserCustomValidator;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/es_dnevnik/student")
public class StudentController {
	@Autowired
	StudentService studentService;
	
	@Autowired
	UserCustomValidator userValidator;
	
	@Autowired
	StudentCustomValidator studentValidator;
	
	@InitBinder
	protected void initBinder(final WebDataBinder binder)
	{
		if (binder.getTarget() instanceof UserEntityDTO) {
			binder.addValidators(userValidator);
		}
		if (binder.getTarget() instanceof StudentAddDto) {
			binder.addValidators(studentValidator);
		}
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> addStudent(@Valid @RequestBody StudentAddDto student,BindingResult result)  {
		try {
		if(result.hasErrors()) {
			FileErrors.appendToFile(createErrorMessage(result));
			return new ResponseEntity<>(createErrorMessage(result),HttpStatus.BAD_REQUEST);
		}else {
			studentValidator.validate(student, result);
		}
		return new ResponseEntity<>(studentService.addStudent(student), HttpStatus.OK);
		}catch (RESTError e) {
			FileErrors.appendToFile(e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> modifyStudent(@PathVariable Integer id,@Valid @RequestBody StudentAddDto student,BindingResult result)  {
		try {
			if(result.hasErrors()) {
				FileErrors.appendToFile(createErrorMessage(result));
				return new ResponseEntity<>(createErrorMessage(result),HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<>(studentService.modify(id, student), HttpStatus.OK);

		} catch (RESTError e) {
			FileErrors.appendToFile(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
		
		@CrossOrigin(origins = "http://localhost:3000")
		@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
		@Secured("ROLE_ADMIN")
		public ResponseEntity<?> removeStudent(@PathVariable Integer id)  {
			try {
				return ResponseEntity.status(HttpStatus.OK).body(studentService.delete(id));
			} catch (RESTError e) {
				FileErrors.appendToFile(e.getMessage());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}

		}
		@CrossOrigin(origins = "http://localhost:3000")
		@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<?> getAllStudent(){
			return ResponseEntity.status(HttpStatus.OK).body(studentService.getAll());
		}
		@CrossOrigin(origins = "http://localhost:3000")
		@RequestMapping(method = RequestMethod.GET,value = "/{studentId}")
		public ResponseEntity<?> getStudentById(@PathVariable Integer studentId) throws RESTError{
			try {
			return ResponseEntity.status(HttpStatus.OK).body(studentService.getById(studentId));
			}catch (RESTError e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
		}
}
		
