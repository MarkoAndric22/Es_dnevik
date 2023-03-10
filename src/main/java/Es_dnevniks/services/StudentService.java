package Es_dnevniks.services;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.StudentEntity;
import Es_dnevniks.entities.dto.StudentEntityDTO;
import Es_dnevniks.entities.dto.UserEntityDTO;

public interface StudentService {

	public StudentEntityDTO addStudent(UserEntityDTO students)throws RESTError;
	public StudentEntityDTO modify(Integer id, UserEntityDTO students) throws RESTError;
	public StudentEntity delete(Integer id)throws RESTError;
}
