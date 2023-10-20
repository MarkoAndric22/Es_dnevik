package Es_dnevniks.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.MarkEntity;
import Es_dnevniks.entities.MarkEnum;
import Es_dnevniks.entities.StudentEntity;
import Es_dnevniks.entities.StudentSubject;
import Es_dnevniks.entities.StudentSubjectMark;
import Es_dnevniks.entities.SubjectEntity;
import Es_dnevniks.entities.dto.MarkEntityDTO;
import Es_dnevniks.entities.dto.SubjectMarksDTO;
import Es_dnevniks.mappers.MarkMapper;
import Es_dnevniks.repository.MarkRepository;
import Es_dnevniks.repository.StudentRepository;
import Es_dnevniks.repository.StudentSubjectMarkRepository;
import Es_dnevniks.repository.StudentSubjectRepository;
import Es_dnevniks.repository.SubjectRepository;

@Service
public class MarkServiceImpl implements MarkService {
	@Autowired
	MarkMapper markMapper;
	@Autowired
	MarkRepository markRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	SubjectRepository subjectRepository;
	@Autowired
	StudentSubjectRepository studentSubjectRepository;
	@Autowired
	StudentSubjectMarkRepository studentSubjectMarkRepository;

	@Override
	public MarkEntityDTO addMark(MarkEntityDTO marks) {

		return markMapper.toDto(markRepository.save(markMapper.toEntity(marks)));
	}

	@Override
	public MarkEntityDTO modify(Integer id, MarkEntityDTO marks) throws RESTError {
		if (markRepository.existsById(id)) {
			return markMapper.toDto(markRepository.save(markMapper.toEntity(marks)));
		}
		throw new RESTError(1, "Mark not exists");
	}

	@Override
	public MarkEntity delete(Integer id) throws RESTError {
		Optional<MarkEntity> markEntity = markRepository.findById(id);
		if (!markEntity.isEmpty()) {
			markRepository.delete(markEntity.get());
			return markEntity.get();
		}

		throw new RESTError(1, "Mark not exists");
	}

//	Ako je korisnik učenik, može da vidi sve svoje ocene.
	@Override
	public List<SubjectMarksDTO> marksForStudents(Integer id) throws RESTError {
		StudentEntity student = studentRepository.findById(id).get();
			
		List<StudentSubject> studentSubject = studentSubjectRepository.findByStudent(student);
		List<SubjectEntity> subjects = studentSubject.stream().map(StudentSubject::getSubject).collect(Collectors.toList());
		List<SubjectMarksDTO> subjectMarksDTOs = new ArrayList<SubjectMarksDTO>();
		
		for(SubjectEntity s : subjects) {
			
			List<StudentSubjectMark> marks = studentSubjectMarkRepository.findByStudentAndSubject(student, s);
			List<MarkEntity> marksStudent = marks.stream().map(StudentSubjectMark::getMark).collect(Collectors.toList());
			
			SubjectMarksDTO subjectMarksDto = new SubjectMarksDTO();
			subjectMarksDto.setSubject(s.getName());
			subjectMarksDto.setMarks(marksStudent);
			subjectMarksDTOs.add(subjectMarksDto);
		}
	
		return subjectMarksDTOs;
	}

	@Override
	public MarkEntity findByMarks(MarkEnum marks) {
		return markRepository.findByMarks(marks);
	}

}
