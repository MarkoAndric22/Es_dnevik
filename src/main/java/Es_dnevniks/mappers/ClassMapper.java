package Es_dnevniks.mappers;

import org.springframework.stereotype.Component;

import Es_dnevniks.entities.ClassEntity;
import Es_dnevniks.entities.dto.ClassEntityDTO;

@Component
public class ClassMapper implements GenericMapper<ClassEntity,ClassEntityDTO> {
	
	@Override
	public ClassEntity toEntity(ClassEntityDTO dto) {
		return new ClassEntity(dto.getName(), dto.getSemester());
	}
	
	@Override
	public ClassEntityDTO toDto(ClassEntity entity) {
		return new ClassEntityDTO(entity.getName(), entity.getSemester());
	}

}
