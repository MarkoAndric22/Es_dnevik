package Es_dnevniks.repository;

import org.springframework.data.repository.CrudRepository;

import Es_dnevniks.entities.NasPredajuPred;
import Es_dnevniks.entities.SubjectEntity;
import Es_dnevniks.entities.TeacherEntity;

public interface NasPredajuPredRepository extends CrudRepository<NasPredajuPred, Integer>{
	
	NasPredajuPred findByTeacherAndSubject(TeacherEntity teacher, SubjectEntity subject);

}
