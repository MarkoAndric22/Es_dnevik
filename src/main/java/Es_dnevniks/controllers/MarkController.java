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
import Es_dnevniks.entities.dto.MarkEntityDTO;
import Es_dnevniks.exception.FileErrors;
import Es_dnevniks.services.MarkService;

@RestController
@RequestMapping(path = "/es_dnevnik/mark")
public class MarkController {

	@Autowired
	MarkService markService;

	@RequestMapping(method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> addMark(@Valid @RequestBody MarkEntityDTO mark, BindingResult result) {
		if (result.hasErrors()) {
			FileErrors.appendToFile(createErrorMessage(result));
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		markService.addMark(mark);
		return new ResponseEntity<>(mark, HttpStatus.OK);
	}

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> modifyMark(@PathVariable Integer id, @Valid @RequestBody MarkEntityDTO mark,
			BindingResult result) {
		try {
			if (result.hasErrors()) {
				FileErrors.appendToFile(createErrorMessage(result));
				return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
			}
			markService.modify(id, mark);
			return new ResponseEntity<>(mark, HttpStatus.OK);

		} catch (RESTError e) {
			FileErrors.appendToFile(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> removeMark(@PathVariable Integer id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(markService.delete(id));
		} catch (RESTError e) {
			FileErrors.appendToFile(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}
//student moze da vidi svoje ocene
	@RequestMapping(method = RequestMethod.GET, value = "studentMarks/{id}")
	@Secured({"ROLE_ADMIN","ROLE_STUDENT"})
	public ResponseEntity<?> marksForStudents(@PathVariable Integer id) throws RESTError {
		return ResponseEntity.status(HttpStatus.OK).body(markService.marksForStudents(id));
	}

}
