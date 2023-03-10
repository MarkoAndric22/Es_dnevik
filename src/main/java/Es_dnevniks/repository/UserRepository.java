package Es_dnevniks.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import Es_dnevniks.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
	public Optional<UserEntity> findByEmail(String email);
	public Optional<UserEntity> findByUsername(String username);
}
