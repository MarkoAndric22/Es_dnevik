package Es_dnevniks.services;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.ClassEntity;
import Es_dnevniks.entities.dto.ClassEntityDTO;

public interface ClassService {
	
	public ClassEntityDTO add(ClassEntityDTO classEntity);
	
	public ClassEntityDTO modify(Integer id,ClassEntityDTO classes)throws RESTError;
	
	public ClassEntity delete(Integer id)throws RESTError;
	

}
