package Es_dnevniks.repository;

import org.springframework.data.repository.CrudRepository;

import Es_dnevniks.entities.ParentEntity;
import Es_dnevniks.entities.StudentEntity;

public interface ParentRepository extends CrudRepository<ParentEntity, Integer> {

}
