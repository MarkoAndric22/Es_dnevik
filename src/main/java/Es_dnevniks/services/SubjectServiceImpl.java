package Es_dnevniks.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.SubjectEntity;
import Es_dnevniks.entities.dto.SubjectEntityDTO;
import Es_dnevniks.mappers.SubjectMapper;
import Es_dnevniks.repository.SubjectRepository;

@Service
public class SubjectServiceImpl implements SubjectService {
	@Autowired
	SubjectRepository subjectRepository;
	@Autowired
	SubjectMapper subjectMapper;

	@Override
	public SubjectEntityDTO add(SubjectEntityDTO subjects) {
		return subjectMapper.toDto(subjectRepository.save(subjectMapper.toEntity(subjects)));
	}

	@Override
	public SubjectEntityDTO modify(Integer id, SubjectEntityDTO subjects) throws RESTError {
		if(subjectRepository.existsById(id)) {
			return subjectMapper.toDto(subjectRepository.save(subjectMapper.toEntity(subjects)));
		}
		throw new RESTError(1, "Subject not exists");
	}

	@Override
	public SubjectEntity delete(Integer id) throws RESTError {
		Optional<SubjectEntity> subjectEntity = subjectRepository.findById(id);
		if (!subjectEntity.isEmpty()) {
			subjectRepository.delete(subjectEntity.get());
			return subjectEntity.get();
		}

		throw new RESTError(1, "Subject not exists");
	}

}
