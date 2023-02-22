package Es_dnevniks.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import Es_dnevniks.entities.ParentEntity;
import Es_dnevniks.entities.StudentEntity;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {

	List<StudentEntity> findByparentStudents(ParentEntity parent);
}
