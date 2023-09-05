package Es_dnevniks.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.ParentEntity;
import Es_dnevniks.entities.ParentStudent;
import Es_dnevniks.entities.RoleEntity;
import Es_dnevniks.entities.StudentEntity;
import Es_dnevniks.entities.UserEntity;
import Es_dnevniks.entities.dto.ListMarksDTO;
import Es_dnevniks.entities.dto.ParentEntityDTO;
import Es_dnevniks.entities.dto.ParentStudentDTO;
import Es_dnevniks.entities.dto.SubjectMarksDTO;
import Es_dnevniks.entities.dto.UserEntityDTO;
import Es_dnevniks.mappers.ParentMapper;
import Es_dnevniks.repository.ParentRepository;
import Es_dnevniks.repository.ParentStudentRepository;
import Es_dnevniks.repository.RoleRepository;
import Es_dnevniks.repository.StudentRepository;
import Es_dnevniks.repository.UserRepository;
import Es_dnevniks.utils.Encryption;

@Service
public class ParentServiceImpl implements ParentService {
	@Autowired
	ParentRepository parentRepository;
	@Autowired
	ParentMapper parentMapper;
	@Autowired
	MarkService markService;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	ParentStudentRepository parentStudentRepository;

	@Override
	public ParentEntityDTO addParent(ParentStudentDTO parents) throws RESTError {

		UserEntity u = new UserEntity();
		RoleEntity r = roleRepository.findByName("ROLE_PARENT");
		u.setName(parents.getFirst_name());
		u.setLastName(parents.getLastName());
		if(userRepository.findByEmail(parents.getEmail()).isPresent()) {
			throw new RESTError(1, "Email must be unique");
		}
		u.setEmail(parents.getEmail());
		if(userRepository.findByUsername(parents.getUsername()).isPresent()) {
			throw new RESTError(1, "username must be unique");
		}
		u.setUsername(parents.getUsername());
		u.setPassword(Encryption.getPassEncoded(parents.getPassword()));
		u.setRole(r);
		ParentEntity p  = new ParentEntity();
		p.setFirst_name(parents.getFirst_name());
		p.setLast_name(parents.getLastName());
		p.setEmail(parents.getEmail());
		p.setUser(u);
		parentRepository.save(p);
		
		    for (StudentEntity student : parents.getStudents()) {
		    	ParentStudent parentStudent = new ParentStudent();
		    	parentStudent.setParent(p);
		    	parentStudent.setStudent(student);
		        parentStudentRepository.save(parentStudent);
		    }

		   return parentMapper.toDto(p);		    
	}

	@Override
	public ParentEntityDTO modify(Integer id, UserEntityDTO parents) throws RESTError {
		if(parentRepository.existsById(id)) {
			
			UserEntity u = userRepository.findByEmail(parents.getEmail()).get();
			RoleEntity r = roleRepository.findByName("ROLE_PARENT");
			u.setName(parents.getFirst_name());
			u.setLastName(parents.getLastName());		
			u.setPassword(Encryption.getPassEncoded(parents.getPassword()));
			u.setRole(r);
			ParentEntity p  = parentRepository.findById(id).get();
			p.setFirst_name(parents.getFirst_name());
			p.setLast_name(parents.getLastName());
			
			p.setUser(u);
			return parentMapper.toDto(parentRepository.save(p));
		}
		throw new RESTError(1,"Parent not exists");
	}

	@Override
	public ParentEntity delete(Integer id) throws RESTError {
		for(ParentEntity p : parentRepository.findAll()) {
			if(p.getId().equals(id)) {
				parentRepository.delete(p);
				Optional<UserEntity> userEntity = userRepository.findByEmail(p.getEmail());
				if(userEntity.isPresent()) {
					userRepository.delete(userEntity.get());
					return p;
				}
			}
		}
		throw new RESTError(1,"Parent not exists");
	}
// roditelj moze da vidi ocene svog deteta
	@Override
	public List<ListMarksDTO> parentMarks(Integer parent_id) throws RESTError {
		ParentEntity parent=parentRepository.findById(parent_id).get();
		List<ParentStudent> ps = parent.getParentStudents();
		if(ps.isEmpty()) {
			throw new RESTError(1,"Student-Parent not exist");
		}
		
		List<StudentEntity> students = new ArrayList<StudentEntity>();
		for(ParentStudent parentStudent: ps) {
			students.add(parentStudent.getStudent());
		}

		List<ListMarksDTO> listMarks = new ArrayList<ListMarksDTO>();
		for(StudentEntity s: students) {
			List<SubjectMarksDTO> marks = markService.marksForStudents(s.getId());
			ListMarksDTO listMark = new ListMarksDTO();
			listMark.setName(s.getFirst_name() + " " + s.getLast_name());
			listMark.setSubjectMarks(marks);
			listMarks.add(listMark);
		}
		return listMarks;
		
	}

	@Override
	public Iterable<ParentEntity> getAllParent() {
		Iterable<ParentEntity> parents=parentRepository.findAll();
				
		return parents;
	}

	@Override
	public ParentEntity getById(Integer ParentId)throws RESTError {
		if (!parentRepository.existsById(ParentId)) {
	        throw new RESTError(1, "Roditelj ne postoji");
	    }
	    
	    Optional<ParentEntity> parent = parentRepository.findById(ParentId);
	    if (parent.isPresent()) {
	        return parent.get();
	    } else {
	        throw new RESTError(2, "Nemoguće dobiti studenta");
	    }
	}
	

	

}
