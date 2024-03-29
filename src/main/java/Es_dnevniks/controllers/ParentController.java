package Es_dnevniks.controllers;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.StudentEntity;
import Es_dnevniks.entities.dto.ParentStudentDTO;
import Es_dnevniks.entities.dto.UserEntityDTO;
import Es_dnevniks.exception.FileErrors;
import Es_dnevniks.services.ParentService;
import Es_dnevniks.utils.UserCustomValidator;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/es_dnevnik/parent")
public class ParentController {
	@Autowired
	ParentService parentService;
	@Autowired
	UserCustomValidator userValidator;
	
	@InitBinder
	protected void initBinder(final WebDataBinder binder)
	{
//	binder.addValidators(userValidator);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> addParent(@Valid @RequestBody ParentStudentDTO parent,BindingResult result)  {
		try {
//		if(result.hasErrors()) {
//			FileErrors.appendToFile(createErrorMessage(result));
//			return new ResponseEntity<>(createErrorMessage(result),HttpStatus.BAD_REQUEST);
//		}else {
//			userValidator.validate(parent, result);
//		}
		return new ResponseEntity<>(parentService.addParent(parent), HttpStatus.OK);
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
	public ResponseEntity<?> modifyParent(@PathVariable Integer id,@Valid @RequestBody UserEntityDTO parent,BindingResult result)  {
		try {
			if(result.hasErrors()) {
				FileErrors.appendToFile(createErrorMessage(result));
				return new ResponseEntity<>(createErrorMessage(result),HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<>(parentService.modify(id, parent), HttpStatus.OK);

		} catch (RESTError e) {
			FileErrors.appendToFile(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> removeParent(@PathVariable Integer id)  {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(parentService.delete(id));
		} catch (RESTError e) {
			FileErrors.appendToFile(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}
//	roditelj moze da vidi ocene svog deteta
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.GET, value = "/getMarks/{id}")
//	@Secured({"ROLE_ADMIN","ROLE_PARENT"})
	public ResponseEntity<?> findMarksByStudents(@PathVariable Integer id) throws RESTError {
		try {
		return ResponseEntity.status(HttpStatus.OK).body(parentService.parentMarks(id));
		}catch(RESTError e) {
			FileErrors.appendToFile(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllParent(){
		return ResponseEntity.status(HttpStatus.OK).body(parentService.getAllParent());
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.GET,value = "/{id}")
	public ResponseEntity<?> getParentById(@PathVariable Integer id) throws RESTError{
		try {
		return ResponseEntity.status(HttpStatus.OK).body(parentService.getById(id));
		}catch (RESTError e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
