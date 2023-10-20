package Es_dnevniks.mappers;

import org.springframework.stereotype.Component;

import Es_dnevniks.entities.StudentEntity;
import Es_dnevniks.entities.dto.StudentEntityDTO;

@Component
public class StudentMapper implements GenericMapper<StudentEntity, StudentEntityDTO> {

	@Override
	public StudentEntity toEntity(StudentEntityDTO dto) {
		return new StudentEntity(dto.getFirst_name(),dto.getLast_name(), dto.getClasses());
	}

	@Override
	public StudentEntityDTO toDto(StudentEntity entity) {
		return new StudentEntityDTO(entity.getFirst_name(),entity.getLast_name(), entity.getClasses());
	}

}
