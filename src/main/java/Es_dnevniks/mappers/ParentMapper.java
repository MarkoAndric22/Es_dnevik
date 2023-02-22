package Es_dnevniks.mappers;

import org.springframework.stereotype.Component;

import Es_dnevniks.entities.ParentEntity;
import Es_dnevniks.entities.dto.ParentEntityDTO;

@Component
public class ParentMapper implements GenericMapper<ParentEntity, ParentEntityDTO> {

	@Override
	public ParentEntity toEntity(ParentEntityDTO dto) {
		return new ParentEntity(dto.getFirst_name(),dto.getLast_name(),dto.getEmail());
	}

	@Override
	public ParentEntityDTO toDto(ParentEntity entity) {
		return new ParentEntityDTO(entity.getFirst_name(),entity.getLast_name(),entity.getEmail());
	}

}
