package Es_dnevniks.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.RoleEntity;
import Es_dnevniks.entities.StudentEntity;
import Es_dnevniks.entities.UserEntity;
import Es_dnevniks.entities.dto.StudentEntityDTO;
import Es_dnevniks.entities.dto.UserEntityDTO;
import Es_dnevniks.mappers.StudentMapper;
import Es_dnevniks.repository.ClassRepository;
import Es_dnevniks.repository.RoleRepository;
import Es_dnevniks.repository.StudentRepository;
import Es_dnevniks.repository.UserRepository;
import Es_dnevniks.utils.Encryption;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	StudentMapper studentMapper;
	@Autowired
	ClassRepository classRepository;

	@Override
	public StudentEntityDTO addStudent(UserEntityDTO students) throws RESTError {
		UserEntity u = new UserEntity();
		RoleEntity r = roleRepository.findByName("ROLE_STUDENT");
		u.setName(students.getFirst_name());
		u.setLastName(students.getLastName());
		if(userRepository.findByEmail(students.getEmail()).isPresent()) {
			throw new RESTError(1, "Email must be unique");
		}
		u.setEmail(students.getEmail());
		if(userRepository.findByUsername(students.getUsername()).isPresent()) {
			throw new RESTError(1, "username must be unique");
		}
		u.setUsername(students.getUsername());
		u.setPassword(Encryption.getPassEncoded(students.getPassword()));
		u.setRole(r);
		StudentEntity s  = new StudentEntity();
		s.setFirst_name(students.getFirst_name());
		s.setLast_name(students.getLastName());
		s.setUser(u);
		
		return studentMapper.toDto(studentRepository.save(s));
	}

	@Override
	public StudentEntityDTO modify(Integer id, UserEntityDTO students) throws RESTError {
		if(studentRepository.existsById(id)) {
			
			UserEntity u = userRepository.findByEmail(students.getEmail()).get();
			RoleEntity r = roleRepository.findByName("ROLE_STUDENT");
			u.setName(students.getFirst_name());
			u.setLastName(students.getLastName());		
			u.setPassword(Encryption.getPassEncoded(students.getPassword()));
			u.setRole(r);
			StudentEntity s  = studentRepository.findById(id).get();
			s.setFirst_name(students.getFirst_name());
			s.setLast_name(students.getLastName());
			
			s.setUser(u);
			return studentMapper.toDto(studentRepository.save(s));
		}
		throw new RESTError(1,"Students not exists");
	}

	@Override
	public StudentEntity delete(Integer id) throws RESTError {
		for(StudentEntity s : studentRepository.findAll()) {
			if(s.getId().equals(id)) {
				studentRepository.delete(s);
				Optional<UserEntity> userEntity = userRepository.findById(id);
				if(userEntity.isPresent()) {
					userRepository.delete(userEntity.get());
					return s;
				}
			}
		}
		throw new RESTError(1,"Student not exists");
	}

}
