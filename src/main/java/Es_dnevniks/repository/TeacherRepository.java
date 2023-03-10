package Es_dnevniks.repository;

import org.springframework.data.repository.CrudRepository;
import Es_dnevniks.entities.TeacherEntity;

public interface TeacherRepository extends CrudRepository<TeacherEntity, Integer> {
}
