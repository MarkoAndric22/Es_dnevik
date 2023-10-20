package Es_dnevniks.services;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.StudentEntity;
import Es_dnevniks.entities.dto.StudentAddDto;
import Es_dnevniks.entities.dto.StudentEntityDTO;
import Es_dnevniks.entities.dto.UserEntityDTO;

public interface StudentService {

	public StudentEntityDTO addStudent(StudentAddDto students)throws RESTError;
	public StudentEntityDTO modify(Integer id, StudentAddDto students) throws RESTError;
	public StudentEntity delete(Integer id)throws RESTError;
	public Iterable<StudentEntity> getAll();
	public StudentEntity getById(Integer studentid)throws RESTError;;
}
