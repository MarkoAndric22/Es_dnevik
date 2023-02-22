package Es_dnevniks.services;

import java.util.Optional;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.TeacherEntity;
import Es_dnevniks.entities.dto.TeacherEntityDTO;


public interface TeacherService {
	
public TeacherEntity addUser(TeacherEntityDTO teacheres);
	
	public TeacherEntity modify(Integer id, TeacherEntityDTO teacheres) throws RESTError;
	
	public TeacherEntity delete(Integer id) throws RESTError;
	
	public Optional<TeacherEntity> teacher(Integer id) throws RESTError;

}
