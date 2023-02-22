package Es_dnevniks.repository;

import org.springframework.data.repository.CrudRepository;

import Es_dnevniks.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
	public UserEntity findByEmail(String email);
}
