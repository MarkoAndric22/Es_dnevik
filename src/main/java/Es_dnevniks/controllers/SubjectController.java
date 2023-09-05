package Es_dnevniks.controllers;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.SubjectEntity;
import Es_dnevniks.entities.dto.SubjectEntityDTO;
import Es_dnevniks.exception.FileErrors;
import Es_dnevniks.repository.SubjectRepository;
import Es_dnevniks.services.SubjectService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/es_dnevnik/subject")
public class SubjectController {
	@Autowired
	SubjectService subjectService;
	@Autowired
	SubjectRepository subjectRepository;
	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> add(@Valid @RequestBody SubjectEntityDTO subject, BindingResult result) throws RESTError {
		if (result.hasErrors()) {
			FileErrors.appendToFile(createErrorMessage(result));
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		subjectService.add(subject);
		return new ResponseEntity<>(subject, HttpStatus.OK);
	}

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));

	}
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> modifySubject(@PathVariable Integer id,@Valid @RequestBody SubjectEntityDTO subject,BindingResult result)  {
		try {
			if(result.hasErrors()) {
				FileErrors.appendToFile(createErrorMessage(result));
				return new ResponseEntity<>(createErrorMessage(result),HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<>(subjectService.modify(id, subject), HttpStatus.OK);

		} catch (RESTError e) {
			FileErrors.appendToFile(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> removeSubject(@PathVariable Integer id)  {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(subjectService.delete(id));
		} catch (RESTError e) {
			FileErrors.appendToFile(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<SubjectEntity>getAllSubject(){
		Iterable<SubjectEntity>subject1= subjectRepository.findAll();
		ArrayList<SubjectEntity>subject2= new ArrayList<>();
		for(SubjectEntity s: subject1) {
			subject2.add(s);
		}
		return subject2;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/{id}")
	public ResponseEntity<?> findSubjectById(@PathVariable Integer id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(subjectService.findSubjectById(id));
		} catch (RESTError e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "by-teacher/{teacherId}")
	public ResponseEntity<?> findSubjectByTeacher(@PathVariable Integer teacherId){
		return ResponseEntity.status(HttpStatus.OK).body(subjectService.subjectByTeacher(teacherId));
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "teacherDontHave/{teacherId}")
	public ResponseEntity<?> subjectTeacherDontHave(@PathVariable Integer teacherId){
		return ResponseEntity.status(HttpStatus.OK).body(subjectService.subjectTeacherDontHave(teacherId));
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "subjectForStudent/{teacherId}")
	public ResponseEntity<?> subjectForStudent(@PathVariable Integer teacherId){
		return ResponseEntity.status(HttpStatus.OK).body(subjectService.subjectForStudent(teacherId));
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "subjectForTeacher/{teacherId}")
	public ResponseEntity<?> subjectForTeacher(@PathVariable Integer teacherId){
		return ResponseEntity.status(HttpStatus.OK).body(subjectService.subjectForStudent(teacherId));
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "addSubjectTeacher")
	public ResponseEntity<?> addSubjectTeacher(@RequestParam Integer teacherId,@RequestParam Integer subjectId){
		return ResponseEntity.status(HttpStatus.OK).body(subjectService.addSubjectTeacher(teacherId, subjectId));
	}
	@RequestMapping(method = RequestMethod.POST,value = "addSubjectStudent")
	public ResponseEntity<?> addSubjectStudent(@RequestParam Integer studentId,@RequestParam Integer subjectId){
		return ResponseEntity.status(HttpStatus.OK).body(subjectService.addSubjectStudent(studentId, subjectId));
	}


}
