package Es_dnevniks.mappers;

import org.springframework.stereotype.Component;

import Es_dnevniks.entities.UserEntity;
import Es_dnevniks.entities.dto.UserEntityDTO;

@Component
public class UserMapper implements GenericMapper<UserEntity, UserEntityDTO> {

	@Override
	public UserEntity toEntity(UserEntityDTO dto) {
		return new UserEntity(dto.getFirst_name(),dto.getLastName(),dto.getEmail(),dto.getPassword(),dto.getUsername(),dto.getRole_name());
	}

	@Override
	public UserEntityDTO toDto(UserEntity entity) {
		return new UserEntityDTO(entity.getName(),entity.getLastName(),entity.getEmail(),entity.getPassword(),entity.getUsername(),entity.getRole());
	}

}
