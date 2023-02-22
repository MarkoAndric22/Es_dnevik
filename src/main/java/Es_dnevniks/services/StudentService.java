package Es_dnevniks.services;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.StudentEntity;
import Es_dnevniks.entities.dto.StudentEntityDTO;

public interface StudentService {

	public StudentEntity addUser(StudentEntityDTO students)throws RESTError;
	public StudentEntity modify(Integer id,StudentEntityDTO students)throws RESTError;
	public StudentEntity delete(Integer id)throws RESTError;
}
