package Es_dnevniks.services;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.SubjectEntity;
import Es_dnevniks.entities.dto.SubjectEntityDTO;

public interface SubjectService {
	
	public SubjectEntityDTO add(SubjectEntityDTO subjects);
	
	public SubjectEntityDTO modify(Integer id, SubjectEntityDTO subjects) throws RESTError;
	
	public SubjectEntity delete(Integer id) throws RESTError;

}
