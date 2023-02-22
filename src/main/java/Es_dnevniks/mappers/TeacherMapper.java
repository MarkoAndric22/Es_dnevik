package Es_dnevniks.mappers;

import org.springframework.stereotype.Component;

import Es_dnevniks.entities.TeacherEntity;
import Es_dnevniks.entities.dto.TeacherEntityDTO;

@Component
public class TeacherMapper implements GenericMapper<TeacherEntity, TeacherEntityDTO> {

	@Override
	public TeacherEntity toEntity(TeacherEntityDTO dto) {
		return new TeacherEntity (dto.getFirst_name(),dto.getLast_name());
	}

	@Override
	public TeacherEntityDTO toDto(TeacherEntity entity) {
		return new TeacherEntityDTO(entity.getFirst_name(),entity.getLast_name());
	}

}
