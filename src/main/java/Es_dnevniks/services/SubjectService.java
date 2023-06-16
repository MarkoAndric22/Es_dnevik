package Es_dnevniks.services;

import java.util.List;
import java.util.Optional;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.SubjectEntity;
import Es_dnevniks.entities.dto.SubjectEntityDTO;

public interface SubjectService {
	
	public SubjectEntityDTO add(SubjectEntityDTO subjects);
	
	public SubjectEntityDTO modify(Integer id, SubjectEntityDTO subjects) throws RESTError;
	
	public SubjectEntity delete(Integer id) throws RESTError;
	
	public Optional<SubjectEntity> findSubjectById(Integer id)throws RESTError;
	
	public List<SubjectEntity> subjectByTeacher(Integer teacherId);
	
	public List<SubjectEntity> subjectTeacherDontHave(Integer teacherId);

}
