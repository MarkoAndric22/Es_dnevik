package Es_dnevniks.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.MarkEntity;
import Es_dnevniks.entities.ParentEntity;
import Es_dnevniks.entities.ParentStudent;
import Es_dnevniks.entities.StudentEntity;
import Es_dnevniks.entities.dto.ListMarksDTO;
import Es_dnevniks.entities.dto.ParentEntityDTO;
import Es_dnevniks.entities.dto.SubjectMarksDTO;
import Es_dnevniks.mappers.ParentMapper;
import Es_dnevniks.repository.ParentRepository;

@Service
public class ParentServiceImpl implements ParentService {
	@Autowired
	ParentRepository parentRepository;
	@Autowired
	ParentMapper parentMapper;
	@Autowired
	MarkService markService;

	@Override
	public ParentEntityDTO addParent(ParentEntityDTO parents) {
		return parentMapper.toDto(parentRepository.save(parentMapper.toEntity(parents)));
	}

	@Override
	public ParentEntityDTO modify(Integer id, ParentEntityDTO parents) throws RESTError {
		if(parentRepository.existsById(id)) {
			return parentMapper.toDto(parentRepository.save(parentMapper.toEntity(parents)));
		}
		throw new RESTError(1,"Parent not exists");
	}

	@Override
	public ParentEntity delete(Integer id) throws RESTError {
		Optional<ParentEntity>parentEntity=parentRepository.findById(id);
		if(!parentEntity.isEmpty()) {
			parentRepository.delete(parentEntity.get());
		}
		throw new RESTError(1,"Parent not exists");
	}

	@Override
	public List<ListMarksDTO> parentMarks(Integer parent_id) throws RESTError {
		ParentEntity parent=parentRepository.findById(parent_id).get();
		List<ParentStudent> ps = parent.getParentStudents();
		if(ps.isEmpty()) {
			throw new RESTError(1,"Students not exist");
		}
		List<StudentEntity> students = new ArrayList<StudentEntity>();
		for(ParentStudent parentStudent: ps) {
			students.add(parentStudent.getStudent());
		}
		ListMarksDTO listMark = new ListMarksDTO();
		List<ListMarksDTO> listMarks = new ArrayList<ListMarksDTO>();
		for(StudentEntity s: students) {
			List<SubjectMarksDTO> marks = markService.marksForStudents(s.getId());
			listMark.setName(s.getFirst_name() + " " + s.getLast_name());
			listMark.setSubjectMarks(marks);
			listMarks.add(listMark);
		}
		return listMarks;
	}

	

	

}
