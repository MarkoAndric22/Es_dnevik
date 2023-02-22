package Es_dnevniks.mappers;

import org.springframework.stereotype.Component;

import Es_dnevniks.entities.MarkEntity;
import Es_dnevniks.entities.dto.MarkEntityDTO;

@Component
public class MarkMapper implements GenericMapper<MarkEntity, MarkEntityDTO> {

	@Override
	public MarkEntity toEntity(MarkEntityDTO dto) {
		
		return new MarkEntity(dto.getMarks());
	}

	@Override
	public MarkEntityDTO toDto(MarkEntity entity) {
		
		return new MarkEntityDTO(entity.getMarks());
	}

}
