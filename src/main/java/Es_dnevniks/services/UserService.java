package Es_dnevniks.services;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.NasPredajuPred;
import Es_dnevniks.entities.StudentSubject;
import Es_dnevniks.entities.TeacherClass;
import Es_dnevniks.entities.UserEntity;
import Es_dnevniks.entities.dto.FamilyDTO;
import Es_dnevniks.entities.dto.StudentClassDTO;
import Es_dnevniks.entities.dto.UserEntityDTO;

public interface UserService {
	
	public UserEntityDTO addUser(UserEntityDTO users) throws RESTError;
	
	public UserEntityDTO modify(Integer id,UserEntityDTO users)throws RESTError;
	
	public UserEntity delete(Integer id)throws RESTError;
	
	public FamilyDTO insertFamily(Integer parentId, Integer studentId);
	
	public StudentSubject adminGivesSubjectToStudent(Integer studentId, Integer subjectId) throws RESTError;
	
	public NasPredajuPred adminGivesSubjectToTeacher(Integer teacherId, Integer subjectId) throws RESTError;
	
	public NasPredajuPred adminRemovesSubjectFromTeacher(Integer teacherId, Integer subjectId);
	
	public StudentClassDTO adminGivesClassToStudent(Integer classId, Integer studentId)throws RESTError;
	
	public TeacherClass adminGivesClasstoTeacher(Integer classId, Integer teacherId)throws RESTError;
	
	public UserEntity findById(Integer id);

}
