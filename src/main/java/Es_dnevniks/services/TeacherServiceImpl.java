package Es_dnevniks.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.ClassEntity;
import Es_dnevniks.entities.EmailObject;
import Es_dnevniks.entities.MarkEntity;
import Es_dnevniks.entities.NasPredajuPred;
import Es_dnevniks.entities.ParentEntity;
import Es_dnevniks.entities.ParentStudent;
import Es_dnevniks.entities.RoleEntity;
import Es_dnevniks.entities.StudentEntity;
import Es_dnevniks.entities.StudentSubject;
import Es_dnevniks.entities.StudentSubjectMark;
import Es_dnevniks.entities.SubjectEntity;
import Es_dnevniks.entities.TeacherClass;
import Es_dnevniks.entities.TeacherEntity;
import Es_dnevniks.entities.UserEntity;
import Es_dnevniks.entities.User_Role;
import Es_dnevniks.entities.dto.ListMarksDTO;
import Es_dnevniks.entities.dto.StudentMarksDTO;
import Es_dnevniks.entities.dto.SubjectMarksDTO;
import Es_dnevniks.entities.dto.TeacherMarksDTO;
import Es_dnevniks.entities.dto.TeacherEntityDTO;
import Es_dnevniks.entities.dto.UserEntityDTO;
import Es_dnevniks.mappers.TeacherMapper;
import Es_dnevniks.repository.MarkRepository;
import Es_dnevniks.repository.NasPredajuPredRepository;
import Es_dnevniks.repository.RoleRepository;
import Es_dnevniks.repository.StudentRepository;
import Es_dnevniks.repository.StudentSubjectMarkRepository;
import Es_dnevniks.repository.StudentSubjectRepository;
import Es_dnevniks.repository.SubjectRepository;
import Es_dnevniks.repository.TeacherRepository;
import Es_dnevniks.repository.UserRepository;
import Es_dnevniks.utils.Encryption;

@Service
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	TeacherMapper teacherMapper;
	@Autowired
	StudentSubjectMarkRepository studentSubjectMarkRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	SubjectRepository subjectRepository;
	@Autowired
	MarkRepository markRepository;
	@Autowired
	StudentSubjectRepository studentSubjectRepository;
	@Autowired
	MarkService markService;
	@Autowired
	NasPredajuPredRepository nasPredajuPredRepository;
	@Autowired
	EmailService emailService;

	@Override
	public TeacherEntityDTO addTeacher(UserEntityDTO teacheres) throws RESTError{
		UserEntity u = new UserEntity();
		RoleEntity r = roleRepository.findByName("ROLE_TEACHER");
		u.setName(teacheres.getFirst_name());
		u.setLastName(teacheres.getLastName());
		if(userRepository.findByEmail(teacheres.getEmail()).isPresent()) {
			throw new RESTError(1, "Email must be unique");
		}
		u.setEmail(teacheres.getEmail());
		if(userRepository.findByUsername(teacheres.getUsername()).isPresent()) {
			throw new RESTError(1, "username must be unique");
		}
		u.setUsername(teacheres.getUsername());
		u.setPassword(Encryption.getPassEncoded(teacheres.getPassword()));
		u.setRole(r);
		TeacherEntity t  = new TeacherEntity();
		t.setFirst_name(teacheres.getFirst_name());
		t.setLast_name(teacheres.getLastName());
		t.setUser(u);
		
		return teacherMapper.toDto(teacherRepository.save(t));
	}

	@Override
	public TeacherEntityDTO modify(Integer id, UserEntityDTO teacheres) throws RESTError {
		if(teacherRepository.existsById(id)) {
			UserEntity u = userRepository.findByEmail(teacheres.getEmail()).get();
			RoleEntity r = roleRepository.findByName("ROLE_TEACHER");
			u.setName(teacheres.getFirst_name());
			u.setLastName(teacheres.getLastName());	
			if(!teacheres.getPassword().equals(u.getPassword())) {
				u.setPassword(Encryption.getPassEncoded(teacheres.getPassword()));
			}
			
			u.setRole(r);
			TeacherEntity p  = teacherRepository.findById(id).get();
			p.setFirst_name(teacheres.getFirst_name());
			p.setLast_name(teacheres.getLastName());
			
			p.setUser(u);
			return teacherMapper.toDto(teacherRepository.save(p));
		}
		throw new RESTError(1,"Teacher not exists");
	}

	@Override
	public TeacherEntity delete(Integer id) throws RESTError {
		for(TeacherEntity t : teacherRepository.findAll()) {
			if(t.getId().equals(id)) {
				teacherRepository.delete(t);
				Optional<UserEntity> userEntity = userRepository.findById(id);
				if(userEntity.isPresent()) {
					userRepository.delete(userEntity.get());
					return t;
				}
			}
		}
		throw new RESTError(1,"Teacher not exists");
	}

	
//	nastavnik daje ocenu uceniku
	@Override
	public StudentMarksDTO teacherEvaluatesStudent(Integer teacherId, Integer studentId, Integer markId, Integer subjectId) throws Exception {
		StudentMarksDTO sm = new StudentMarksDTO();
		if(!studentRepository.existsById(studentId)) {
			throw new RESTError(1,"Student doesnt exist");
		}
		StudentEntity student = studentRepository.findById(studentId).get();
		
		List<StudentSubject> ss = studentSubjectRepository.findByStudent(student);
		List<SubjectEntity> subjects = ss.stream().map(StudentSubject::getSubject).collect(Collectors.toList());
		if(!subjects.contains(subjectRepository.findById(subjectId).get())) {
			throw new RESTError(1,"Subject is not allowed for that student");
		}
		SubjectEntity subject = subjectRepository.findById(subjectId).get();
		MarkEntity mark = markRepository.findById(markId).get();
		
		List<NasPredajuPred> teacherSubject = subject.getNasPredajuPreds();
		List<TeacherEntity> teachers = teacherSubject.stream().map(NasPredajuPred::getTeacher).collect(Collectors.toList());
		
		TeacherEntity teacher = new TeacherEntity();
		UserEntity user = new UserEntity();
		if(teacherRepository.findById(teacherId).isPresent()) {
			teacher = teacherRepository.findById(teacherId).get();
			List<TeacherClass> teacherClass = teacher.getTeacherClass();
			List<ClassEntity> classes = teacherClass.stream().map(TeacherClass::getClasses).collect(Collectors.toList());
			if(teachers.contains(teacher) && classes.contains(student.getClasses())){
					sm.setStudent(student.getFirst_name() + " " + student.getLast_name());
					sm.setSubject(subject.getName());
					sm.setMark(mark.getMarks());
					StudentSubjectMark studentSubjectMark =  new StudentSubjectMark(student, subject, mark);
					studentSubjectMarkRepository.save(studentSubjectMark);
					
				} else {
					throw new RESTError(1,"Teacher is not allowed to insert that mark");
				}
		}
		else if(userRepository.findById(teacherId).isPresent()) {
			user = userRepository.findById(teacherId).get();
			if(user.getRole().getName().equals(User_Role.ROLE_ADMIN.toString())){
					
					sm.setStudent(student.getFirst_name() + " " + student.getLast_name());
					sm.setSubject(subject.getName());
					sm.setMark(mark.getMarks());
					StudentSubjectMark studentSubjectMark =  new StudentSubjectMark(student, subject, mark);
					studentSubjectMarkRepository.save(studentSubjectMark);
					
				} else {
					throw new RESTError(1,"Teacher is not allowed to insert that mark");
				}
		} 
		
			

		List<ParentStudent> parentStudent = student.getParentStudents();
		List<ParentEntity> parents = parentStudent.stream().map(ParentStudent::getParent).collect(Collectors.toList());
		
		for(ParentEntity p:parents) {
			EmailObject email = new EmailObject();
			email.setTo(p.getEmail());
			email.setSubject("Students new mark");
			email.setText("Hello mr/mrs " + p.getFirst_name() + " " + p.getLast_name() + ", student " + student.getFirst_name() + " " + student.getLast_name() +
					" got mark " + mark.getMarks() + " in the subject " + subject.getName() +
					" from the professor " + teacher.getFirst_name() + " " + teacher.getLast_name() + ". Best regards, admin" );
			emailService.sendSimpleMessage(email);
		}
		
	
		
		return sm;
	}
	
	@Override
	public List<TeacherMarksDTO> teacherMarks(Integer teacher_id) throws RESTError {
       
		TeacherEntity teacher = teacherRepository.findById(teacher_id).get();
		List<SubjectEntity> subjects = teacher.getNasPredajuPreds().stream().map(NasPredajuPred::getSubject).collect(Collectors.toList());
		List<ClassEntity> classes = teacher.getTeacherClass().stream().map(TeacherClass::getClasses).collect(Collectors.toList());

		List<StudentEntity> students = new ArrayList<StudentEntity>();
		
		for(StudentEntity s : studentRepository.findAll()) {
			if(classes.contains(s.getClasses())) {
				students.add(s);
			}
		}
		List<TeacherMarksDTO> teacherMarks = new ArrayList<TeacherMarksDTO>();
		for(SubjectEntity s:subjects) {
			TeacherMarksDTO tm = new TeacherMarksDTO();
			tm.setSubject(s.getName());
			Map<String, List<MarkEntity>> sm = new TreeMap<String, List<MarkEntity>>();
			for(StudentEntity st:students) {
				List<StudentSubjectMark> marks = studentSubjectMarkRepository.findByStudentAndSubject(st, s);
				List<MarkEntity> marksStudent = marks.stream().map(StudentSubjectMark::getMark).collect(Collectors.toList());
				sm.put(st.getFirst_name() + " " + st.getLast_name(), marksStudent);
				tm.setStudentMarks(sm);
			}
			teacherMarks.add(tm);
		}
		return teacherMarks;
	}

	@Override
	public TeacherMarksDTO modifyMarks(Integer ssm_id, Integer mark) throws RESTError {
		MarkEntity markEntity = markRepository.findById(mark).get();
		StudentSubjectMark ssm = studentSubjectMarkRepository.findById(ssm_id).get();
		ssm.setMark(markEntity);
		List<MarkEntity> marks = studentSubjectMarkRepository.findByStudentAndSubject(ssm.getStudent(), ssm.getSubject()).stream().map(StudentSubjectMark::getMark).collect(Collectors.toList());
		
		Map<String, List<MarkEntity>> sm = new TreeMap<String, List<MarkEntity>>();
		sm.put(ssm.getStudent().getFirst_name() + " " + ssm.getStudent().getLast_name(), marks);
		TeacherMarksDTO tm = new TeacherMarksDTO();
		tm.setSubject(ssm.getSubject().getName());
		tm.setStudentMarks(sm);
		studentSubjectMarkRepository.save(ssm);
		return tm;
	}

	@Override
	public TeacherMarksDTO deleteMark(Integer ssm_id) throws RESTError {
		if(!studentSubjectMarkRepository.existsById(ssm_id)) {
			throw new RESTError(1,"Mark doesnt exist");
		}
		
		StudentSubjectMark ssm = studentSubjectMarkRepository.findById(ssm_id).get();
		studentSubjectMarkRepository.delete(ssm);
		List<MarkEntity> marks = studentSubjectMarkRepository.findByStudentAndSubject(ssm.getStudent(), ssm.getSubject()).stream().map(StudentSubjectMark::getMark).collect(Collectors.toList());
		
		Map<String, List<MarkEntity>> sm = new TreeMap<String, List<MarkEntity>>();
		sm.put(ssm.getStudent().getFirst_name() + " " + ssm.getStudent().getLast_name(), marks);
		TeacherMarksDTO tm = new TeacherMarksDTO();
		tm.setSubject(ssm.getSubject().getName());
		tm.setStudentMarks(sm);
		return tm;
	}

	@Override
	public List<TeacherMarksDTO> search(Integer teacher_id, String subject) throws RESTError {
		List<TeacherMarksDTO> tmList = new ArrayList<TeacherMarksDTO>();
		for(TeacherMarksDTO tm : this.teacherMarks(teacher_id)) {
			if(tm.getSubject().equals(subject)) {
				tmList.add(tm);
			}
		}
		return tmList;
	}

	@Override
	public List<TeacherEntity> teachersBySubject(Integer subjectId){
		SubjectEntity subject = subjectRepository.findById(subjectId).get();
		List<TeacherEntity> teachers = subject.getNasPredajuPreds().stream().map(NasPredajuPred::getTeacher).collect(Collectors.toList());
		return teachers;
		
	}


}