package Es_dnevniks.services;

import java.util.List;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.MarkEntity;
import Es_dnevniks.entities.dto.MarkEntityDTO;
import Es_dnevniks.entities.dto.SubjectMarksDTO;

public interface MarkService {
	
	public MarkEntityDTO addMark(MarkEntityDTO marks);
	
	public MarkEntityDTO modify(Integer id, MarkEntityDTO marks) throws RESTError;
	
	public MarkEntity delete(Integer id) throws RESTError;
	
	public List<SubjectMarksDTO> marksForStudents(Integer id)throws RESTError;
	

}
