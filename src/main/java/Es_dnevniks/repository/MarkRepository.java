package Es_dnevniks.repository;

import org.springframework.data.repository.CrudRepository;

import Es_dnevniks.entities.MarkEntity;
import Es_dnevniks.entities.MarkEnum;

public interface MarkRepository extends CrudRepository<MarkEntity, Integer> {

	MarkEntity findByMarks(MarkEnum marks);
}
