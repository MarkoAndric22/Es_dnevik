package Es_dnevniks.controllers;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.dto.SubjectEntityDTO;
import Es_dnevniks.entities.dto.UserEntityDTO;
import Es_dnevniks.exception.FileErrors;
import Es_dnevniks.services.SubjectService;


@RestController
@RequestMapping(path = "/es_dnevnik/subject")
public class SubjectController {
	@Autowired
	SubjectService subjectService;
	
	@RequestMapping(method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> addMark(@Valid @RequestBody SubjectEntityDTO subject, BindingResult result) throws RESTError {
		if (result.hasErrors()) {
			FileErrors.appendToFile(createErrorMessage(result));
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		subjectService.add(subject);
		return new ResponseEntity<>(subject, HttpStatus.OK);
	}

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));

	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> modifySubject(@PathVariable Integer id,@Valid @RequestBody SubjectEntityDTO subject,BindingResult result)  {
		try {
			if(result.hasErrors()) {
				FileErrors.appendToFile(createErrorMessage(result));
				return new ResponseEntity<>(createErrorMessage(result),HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<>(subjectService.modify(id, subject), HttpStatus.OK);

		} catch (RESTError e) {
			FileErrors.appendToFile(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> removeParent(@PathVariable Integer id)  {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(subjectService.delete(id));
		} catch (RESTError e) {
			FileErrors.appendToFile(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

}
