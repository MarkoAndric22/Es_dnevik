package Es_dnevniks.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.ClassEntity;
import Es_dnevniks.entities.NasPredajuPred;
import Es_dnevniks.entities.ParentEntity;
import Es_dnevniks.entities.ParentStudent;
import Es_dnevniks.entities.RoleEntity;
import Es_dnevniks.entities.StudentEntity;
import Es_dnevniks.entities.StudentSubject;
import Es_dnevniks.entities.SubjectEntity;
import Es_dnevniks.entities.TeacherClass;
import Es_dnevniks.entities.TeacherEntity;
import Es_dnevniks.entities.UserEntity;
import Es_dnevniks.entities.dto.FamilyDTO;
import Es_dnevniks.entities.dto.StudentClassDTO;
import Es_dnevniks.entities.dto.UserEntityDTO;
import Es_dnevniks.mappers.UserMapper;
import Es_dnevniks.repository.ClassRepository;
import Es_dnevniks.repository.NasPredajuPredRepository;
import Es_dnevniks.repository.ParentRepository;
import Es_dnevniks.repository.ParentStudentRepository;
import Es_dnevniks.repository.RoleRepository;
import Es_dnevniks.repository.StudentRepository;
import Es_dnevniks.repository.StudentSubjectRepository;
import Es_dnevniks.repository.SubjectRepository;
import Es_dnevniks.repository.TeacherClassRepository;
import Es_dnevniks.repository.TeacherRepository;
import Es_dnevniks.repository.UserRepository;
import Es_dnevniks.utils.Encryption;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	StudentSubjectRepository studentSubjectRepository;
	@Autowired
	SubjectRepository subjectRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	ParentStudentRepository parentStudentRepository;
	@Autowired
	ParentRepository parentRepository;
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	NasPredajuPredRepository nasPredajuPredRepository;
	@Autowired
	ClassRepository classRepository;
	@Autowired
	TeacherClassRepository teacherClassRepository;
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public UserEntityDTO addUser(UserEntityDTO users) throws RESTError{
		UserEntity u = new UserEntity();
		RoleEntity r = roleRepository.findByName("ROLE_ADMIN");
		u.setName(users.getFirst_name());
		u.setLastName(users.getLastName());
		if(userRepository.findByEmail(users.getEmail()).isPresent()) {
			throw new RESTError(1, "Email must be unique");
		}
		u.setEmail(users.getEmail());
		if(userRepository.findByUsername(users.getUsername()).isPresent()) {
			throw new RESTError(1, "username must be unique");
		}
		u.setUsername(users.getUsername());
		u.setPassword(Encryption.getPassEncoded(users.getPassword()));
		u.setRole(r);
		return userMapper.toDto(userRepository.save(u));
	}

	@Override
	public UserEntityDTO modify(Integer id, UserEntityDTO users) throws RESTError {
		if(userRepository.existsById(id)) {
			return userMapper.toDto(userRepository.save(userMapper.toEntity(users)));
		}
		throw new RESTError(1, "User not exists");
	}

	@Override
	public UserEntity delete(Integer id) throws RESTError {
		Optional<UserEntity> userEntity = userRepository.findById(id);
		if (!userEntity.isEmpty()) {
			userRepository.delete(userEntity.get());
			return userEntity.get();
		}

		throw new RESTError(1, "User not exists");
	}
	

	@Override
	public FamilyDTO insertFamily(Integer parentId, Integer studentId) {
		ParentEntity parent = parentRepository.findById(parentId).get();
		StudentEntity student = studentRepository.findById(studentId).get();
		ParentStudent ps = new ParentStudent(parent, student);
		parentStudentRepository.save(ps);
		FamilyDTO family = new FamilyDTO(parent.getFirst_name() + " " + parent.getLast_name(), student.getFirst_name() + " " + student.getLast_name());
		return family;
	}

	@Override
	public StudentSubject adminGivesSubjectToStudent(Integer studentId, Integer subjectId) throws RESTError {
		SubjectEntity subject = subjectRepository.findById(subjectId).get();
		StudentEntity student = studentRepository.findById(studentId).get();
		StudentSubject ss = new StudentSubject();
		ss.setStudent(student);
		ss.setSubject(subject);
		for(StudentSubject s:studentSubjectRepository.findAll()) {
			if(s.getStudent().equals(student) && s.getSubject().equals(subject)) {
				throw new RESTError(1, "Student already have that subject");
			}
		}
		
		return studentSubjectRepository.save(ss);
	}

	@Override
	public NasPredajuPred adminGivesSubjectToTeacher(Integer teacherId, Integer subjectId) throws RESTError {
		SubjectEntity subject = subjectRepository.findById(subjectId).get();
		TeacherEntity teacher = teacherRepository.findById(teacherId).get();
		NasPredajuPred predaju = new NasPredajuPred();
		predaju.setSubject(subject);
		predaju.setTeacher(teacher);
		for(NasPredajuPred npp : nasPredajuPredRepository.findAll()) {
			if(npp.getTeacher().equals(teacher) && npp.getSubject().equals(subject)) {
				throw new RESTError(1, "Teacher already have that subject");
			}
		}
		return nasPredajuPredRepository.save(predaju);
	}

	@Override
	public StudentClassDTO adminGivesClassToStudent(Integer classId, Integer studentId) throws RESTError {
		ClassEntity classes= classRepository.findById(classId).get();
		StudentEntity student=studentRepository.findById(studentId).get();
		StudentClassDTO sc= new StudentClassDTO();
		sc.setFirst_name(student.getFirst_name());
		sc.setLast_name(student.getLast_name());
		sc.setName(classes.getName());
		for(StudentEntity s: classes.getStudent() )
			if(s.equals(student)) {
				throw new RESTError(1, "Student is already in that class");
			}
		
		return sc;
	}

	@Override
	public TeacherClass adminGivesClasstoTeacher(Integer classId, Integer teacherId) throws RESTError {
		ClassEntity classes= classRepository.findById(classId).get();
		TeacherEntity teacher= teacherRepository.findById(teacherId).get();
		TeacherClass teacherClass=new TeacherClass();
		teacherClass.setClasses(classes);
		teacherClass.setTeacher(teacher);
		for(TeacherClass tc:teacherClassRepository.findAll()) {
			if(tc.getClasses().equals(classes)&&tc.getTeacher().equals(teacher)) {
				throw new RESTError(1, "Teacher already have that class");
			}
		}
		return teacherClassRepository.save(teacherClass);
	}

	@Override
	public UserEntity findById(Integer id) {
		return userRepository.findById(id).get();
	}

	@Override
	public NasPredajuPred adminRemovesSubjectFromTeacher(Integer teacherId, Integer subjectId) {
		SubjectEntity subject = subjectRepository.findById(subjectId).get();
		TeacherEntity teacher = teacherRepository.findById(teacherId).get();
		NasPredajuPred predaju = nasPredajuPredRepository.findByTeacherAndSubject(teacher, subject);
		nasPredajuPredRepository.deleteById(predaju.getId());
		return predaju;
	}

}
