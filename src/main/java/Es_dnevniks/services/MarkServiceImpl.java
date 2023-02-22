package Es_dnevniks.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.MarkEntity;
import Es_dnevniks.entities.MarkSubject;
import Es_dnevniks.entities.StudentEntity;
import Es_dnevniks.entities.StudentMark;
import Es_dnevniks.entities.StudentSubject;
import Es_dnevniks.entities.StudentSubjectMark;
import Es_dnevniks.entities.SubjectEntity;
import Es_dnevniks.entities.dto.MarkEntityDTO;
import Es_dnevniks.entities.dto.SubjectMarksDTO;
import Es_dnevniks.mappers.MarkMapper;
import Es_dnevniks.repository.MarkRepository;
import Es_dnevniks.repository.StudentRepository;
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
		}

		throw new RESTError(1, "Mark not exists");
	}

//	Ako je korisnik učenik, može da vidi sve svoje ocene.
	@Override
	public List<SubjectMarksDTO> marksForStudents(Integer id) throws RESTError {
		StudentEntity student = studentRepository.findById(id).get();
		List<StudentSubjectMark> studentSubjects = student.getStudentSubjectMark();
		List<SubjectEntity> subjects = new ArrayList<SubjectEntity>();
		List<SubjectMarksDTO> subjectMarksDto = new ArrayList<SubjectMarksDTO>();
		List<MarkEntity> marks = new ArrayList<MarkEntity>();
		SubjectMarksDTO smDTO = new SubjectMarksDTO();
		for(StudentSubjectMark ssm : studentSubjects) {
			SubjectEntity subject = subjectRepository.findById(ssm.getSubject().getId()).get();
			subjects.add(subject);smDTO.setSubject(subject.getName());		
			marks.add(ssm.getMark());
			
			
			
			smDTO.setMarks(marks);
			
			subjectMarksDto.add(smDTO);
			smDTO = new SubjectMarksDTO();
		}
//		for(SubjectEntity s : subjects) {
//			List<MarkSubject> ms = s.getMarkSubject();
//			SubjectMarksDTO smDTO = new SubjectMarksDTO();
//
//			smDTO.setSubject(s.getName());
//			for (MarkSubject m : ms) {
//				marks.add(m.getMark());
//				smDTO.setMarks(marks);
//
//			}
//			subjectMarksDto.add(smDTO);
//		}
		
//		StudentEntity student = studentRepository.findById(id).get();
//		List<StudentMark> marks = student.getStudentMark();
//		List<StudentSubject> studentSubjects = student.getStudentSubj();
//		List<SubjectEntity> subjects = new ArrayList<SubjectEntity>();
//
//		for (StudentSubject ss : studentSubjects) {
//			subjects.add(ss.getSubject());
//		}
//
//		List<MarkEntity> markEntity = new ArrayList<MarkEntity>();
//
//		List<SubjectMarksDTO> subjectMarksDto = new ArrayList<SubjectMarksDTO>();
//		for (SubjectEntity s : subjects) {
//			List<MarkSubject> ms = s.getMarkSubject();
//			SubjectMarksDTO smDTO = new SubjectMarksDTO();
//
//			smDTO.setSubject(s.getName());
//			for (MarkSubject m : ms) {
//				markEntity.add(m.getMark());
//				smDTO.setMarks(markEntity);
//
//			}
//			subjectMarksDto.add(smDTO);
//		}

//		System.out.println(Arrays.asList(subjects));
//		if(marks.isEmpty()) {
//		throw new RESTError(1,"Mark not exists");
//		}

//		for(StudentMark s:marks) {
//			markEntity.add(s.getMark());
//		}
		return subjectMarksDto;
	}

}
