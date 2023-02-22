package Es_dnevniks.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import Es_dnevniks.entities.MarkEntity;
import Es_dnevniks.entities.StudentEntity;

public interface MarkRepository extends CrudRepository<MarkEntity, Integer> {

	List<MarkEntity>findBystudentMarks(StudentEntity student);
}
