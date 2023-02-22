package Es_dnevniks.services;

import java.util.List;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.ParentEntity;
import Es_dnevniks.entities.StudentEntity;
import Es_dnevniks.entities.dto.ListMarksDTO;
import Es_dnevniks.entities.dto.ParentEntityDTO;



public interface ParentService {
	
	public ParentEntityDTO addParent(ParentEntityDTO parents);
	
	public ParentEntityDTO modify(Integer id,ParentEntityDTO parents)throws RESTError;
	
	public ParentEntity delete(Integer id)throws RESTError;
	
	public List<ListMarksDTO> parentMarks(Integer parent_id) throws RESTError;

}
