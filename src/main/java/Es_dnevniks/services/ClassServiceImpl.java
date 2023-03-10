package Es_dnevniks.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.ClassEntity;
import Es_dnevniks.entities.dto.ClassEntityDTO;
import Es_dnevniks.mappers.ClassMapper;
import Es_dnevniks.repository.ClassRepository;

@Service
public class ClassServiceImpl implements ClassService{
	@Autowired
	ClassRepository classRepository;
	@Autowired
	ClassMapper classMapper;

	@Override
	public ClassEntityDTO add(ClassEntityDTO classEntity) {
		return classMapper.toDto(classRepository.save(classMapper.toEntity(classEntity)));
	}

	@Override
	public ClassEntityDTO modify(Integer id, ClassEntityDTO classes) throws RESTError {
		if (classRepository.existsById(id)) {
			return classMapper.toDto(classRepository.save(classMapper.toEntity(classes)));
		}
		throw new RESTError(1, "Class not exists");
	}
	

	@Override
	public ClassEntity delete(Integer id) throws RESTError {
		Optional<ClassEntity> classEntity = classRepository.findById(id);
		if (!classEntity.isEmpty()) {
			classRepository.delete(classEntity.get());
			return classEntity.get();
		}

		throw new RESTError(1, "Class not exists");
	}

	
}
