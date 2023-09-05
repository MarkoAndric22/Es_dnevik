package Es_dnevniks.services;

import java.util.List;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.ParentEntity;
import Es_dnevniks.entities.StudentEntity;
import Es_dnevniks.entities.dto.ListMarksDTO;
import Es_dnevniks.entities.dto.ParentEntityDTO;
import Es_dnevniks.entities.dto.ParentStudentDTO;
import Es_dnevniks.entities.dto.UserEntityDTO;

public interface ParentService {
	
	public ParentEntityDTO addParent(ParentStudentDTO parents) throws RESTError;
	
	public ParentEntityDTO modify(Integer id,UserEntityDTO parents)throws RESTError;
	
	public ParentEntity delete(Integer id)throws RESTError;
	
	public List<ListMarksDTO> parentMarks(Integer parent_id) throws RESTError;
	
	public Iterable<ParentEntity> getAllParent();
	
	public ParentEntity getById(Integer ParentId)throws RESTError;

}
