package Es_dnevniks.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import Es_dnevniks.entities.StudentEntity;
import Es_dnevniks.entities.StudentSubject;
import Es_dnevniks.entities.SubjectEntity;

public interface StudentSubjectRepository extends CrudRepository<StudentSubject, Integer> {
	
	List<StudentSubject> findByStudent(StudentEntity student);
	
	List<StudentSubject> findBySubject(SubjectEntity subject);

}
