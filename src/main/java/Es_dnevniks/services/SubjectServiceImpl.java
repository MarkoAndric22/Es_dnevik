package Es_dnevniks.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.NasPredajuPred;
import Es_dnevniks.entities.StudentEntity;
import Es_dnevniks.entities.StudentSubject;
import Es_dnevniks.entities.SubjectEntity;
import Es_dnevniks.entities.TeacherEntity;
import Es_dnevniks.entities.dto.SubjectEntityDTO;
import Es_dnevniks.mappers.SubjectMapper;
import Es_dnevniks.repository.NasPredajuPredRepository;
import Es_dnevniks.repository.StudentRepository;
import Es_dnevniks.repository.StudentSubjectMarkRepository;
import Es_dnevniks.repository.StudentSubjectRepository;
import Es_dnevniks.repository.SubjectRepository;
import Es_dnevniks.repository.TeacherRepository;

@Service
public class SubjectServiceImpl implements SubjectService {
	@Autowired
	SubjectRepository subjectRepository;
	@Autowired
	SubjectMapper subjectMapper;
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	StudentSubjectMarkRepository studentSubjectMarkRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	NasPredajuPredRepository nasPredajuPredRepository;
	@Autowired
	StudentSubjectRepository studentSubjectRepository;

	@Override
	public SubjectEntityDTO add(SubjectEntityDTO subjects) {
		return subjectMapper.toDto(subjectRepository.save(subjectMapper.toEntity(subjects)));
	}

	@Override
	public SubjectEntityDTO modify(Integer id, SubjectEntityDTO subjects) throws RESTError {
		if(subjectRepository.existsById(id)) {
			SubjectEntity sub = subjectRepository.findById(id).get();
			sub.setName(subjects.getName());
			sub.setFond(subjects.getFond());
			return subjectMapper.toDto(subjectRepository.save(sub));
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

	@Override
	public Optional<SubjectEntity> findSubjectById(Integer id) throws RESTError {
	    Optional<SubjectEntity> subject = subjectRepository.findById(id);
	    if (subject.isPresent()) {
	        return subject;
	    } else {
	        throw new RESTError(1, "Subject not exists");
	    }
	}
	
	@Override
	public List<SubjectEntity> subjectByTeacher(Integer teacherId){
		List<SubjectEntity> subjects = new ArrayList<SubjectEntity>();
		if(teacherRepository.existsById(teacherId)) {
			TeacherEntity teacher = teacherRepository.findById(teacherId).get();
			subjects = teacher.getNasPredajuPreds().stream().map(NasPredajuPred::getSubject).collect(Collectors.toList());
		}
		
		return subjects;
		
	}

	@Override
	public List<SubjectEntity> subjectTeacherDontHave(Integer teacherId) {
		List<SubjectEntity> subjects = new ArrayList<SubjectEntity>();
		for(SubjectEntity subjectEntity : subjectRepository.findAll()) {
			if(!subjectByTeacher(teacherId).contains(subjectEntity)) {
				subjects.add(subjectEntity);
			}
		}
		return subjects;
	}

	@Override
	public List<SubjectEntity> subjectForStudent(Integer studentId){
		List<SubjectEntity> subjects = new ArrayList<SubjectEntity>();
		if(studentRepository.existsById(studentId)) {
			StudentEntity studentEntity= studentRepository.findById(studentId).get();
			subjects = studentEntity.getStudentSubj().stream().map(StudentSubject::getSubject).collect(Collectors.toList());
		}
		return subjects;
	}

	@Override
	public List<SubjectEntity> subjectForTeacher(Integer teacherId) {
		List<SubjectEntity> subjects = new ArrayList<SubjectEntity>();
		if(teacherRepository.existsById(teacherId)) {
			TeacherEntity teacherEntity= teacherRepository.findById(teacherId).get();
			subjects= teacherEntity.getNasPredajuPreds().stream().map(NasPredajuPred :: getSubject).collect(Collectors.toList());
		}
		return subjects;
	}

	@Override
	public SubjectEntity addSubjectTeacher(Integer teacherId, Integer subjectId) {
	
		TeacherEntity teacher=teacherRepository.findById(teacherId).get();
		SubjectEntity subject=subjectRepository.findById(subjectId).get();
		NasPredajuPred nasPredajuPred= new NasPredajuPred();
		nasPredajuPred.setSubject(subject);
		nasPredajuPred.setTeacher(teacher);
		nasPredajuPredRepository.save(nasPredajuPred);
		return subject;
	 
	}

	@Override
	public SubjectEntity addSubjectStudent(Integer studentId, Integer subjectId) {
		StudentEntity student= studentRepository.findById(studentId).get();
		SubjectEntity subject=subjectRepository.findById(subjectId).get();
		StudentSubject studentSubject=new StudentSubject();
		studentSubject.setStudent(student);
		studentSubject.setSubject(subject);
		studentSubjectRepository.save(studentSubject);
		return subject;
	}


}

	

	


