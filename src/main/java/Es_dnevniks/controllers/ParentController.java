package Es_dnevniks.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.dto.ListMarksDTO;
import Es_dnevniks.services.ParentService;

@RestController
public class ParentController {
	@Autowired
	ParentService parentService;
	
	@GetMapping("getMarks/{id}")
	public List<ListMarksDTO> findMarksByStudents(@PathVariable Integer id) throws RESTError {
		return parentService.parentMarks(id);
	}

}
