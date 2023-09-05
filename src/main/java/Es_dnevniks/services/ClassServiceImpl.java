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
		if (!classRepository.existsById(id)) {
			throw new RESTError(1, "Class with the given id does not exist.");
		}
		 
		    ClassEntity existingClass = classRepository.findById(id).get();

	   
	    existingClass.setName(classes.getName());
	    existingClass.setSemester(classes.getSemester());
	    
	    ClassEntity savedClass=classRepository.save(existingClass);
	    
	    return classMapper.toDto(savedClass);
	    
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
