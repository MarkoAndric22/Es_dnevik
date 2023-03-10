package Es_dnevniks.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import Es_dnevniks.entities.StudentEntity;
import Es_dnevniks.entities.StudentSubjectMark;
import Es_dnevniks.entities.SubjectEntity;

public interface StudentSubjectMarkRepository extends CrudRepository<StudentSubjectMark, Integer> {

	List<StudentSubjectMark> findByStudentAndSubject(StudentEntity student, SubjectEntity subject);
	
	List<StudentSubjectMark> findByStudent(StudentEntity student);
}
