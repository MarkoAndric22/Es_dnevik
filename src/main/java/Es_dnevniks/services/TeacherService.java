package Es_dnevniks.services;

import java.util.List;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.TeacherEntity;
import Es_dnevniks.entities.dto.StudentMarksDTO;
import Es_dnevniks.entities.dto.TeacherMarksDTO;
import Es_dnevniks.entities.dto.TeacherEntityDTO;
import Es_dnevniks.entities.dto.UserEntityDTO;


public interface TeacherService {
	
	public TeacherEntityDTO addTeacher(UserEntityDTO teacheres)throws RESTError;
	
	public TeacherEntityDTO modify(Integer id, UserEntityDTO teacheres) throws RESTError;
	
	public TeacherEntity delete(Integer id) throws RESTError;
	
	public StudentMarksDTO teacherEvaluatesStudent (Integer teacherId, Integer studentId, Integer markId, Integer subjectId)  throws RESTError,Exception;
	
	public List<TeacherMarksDTO> teacherMarks(Integer teacher_id) throws RESTError;
	
	public TeacherMarksDTO modifyMarks(Integer ssm_id, Integer mark) throws RESTError;
	
	public TeacherMarksDTO deleteMark(Integer ssm_id) throws RESTError;
	
	public List<TeacherMarksDTO> search(Integer teacher_id, String subject) throws RESTError;
	
	public List<TeacherEntity> teachersBySubject(Integer subjectId);

	
}
