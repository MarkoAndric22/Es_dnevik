package Es_dnevniks.repository;

import org.springframework.data.repository.CrudRepository;

import Es_dnevniks.entities.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity, Integer>{

	public RoleEntity findByName(String name);
}
