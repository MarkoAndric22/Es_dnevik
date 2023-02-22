package Es_dnevniks.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.dto.SubjectMarksDTO;
import Es_dnevniks.services.MarkService;

@RestController
public class MarkController {
	
	@Autowired
	MarkService markService;
	
	@GetMapping("studentMarks/{id}")
	public List<SubjectMarksDTO> marksForStudents(@PathVariable Integer id) throws RESTError{
		return markService.marksForStudents(id);
	}

}
