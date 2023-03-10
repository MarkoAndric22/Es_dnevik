package Es_dnevniks.mappers;

import org.springframework.stereotype.Component;

import Es_dnevniks.entities.SubjectEntity;
import Es_dnevniks.entities.dto.SubjectEntityDTO;

@Component
public class SubjectMapper implements GenericMapper<SubjectEntity, SubjectEntityDTO> {

	@Override
	public SubjectEntity toEntity(SubjectEntityDTO dto) {
		return new SubjectEntity(dto.getName(),dto.getFond());
	}

	@Override
	public SubjectEntityDTO toDto(SubjectEntity entity) {
		return new SubjectEntityDTO(entity.getName(),entity.getFond());
	}

}
