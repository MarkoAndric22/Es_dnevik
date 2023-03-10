package Es_dnevniks.entities.dto;

import java.util.List;
import java.util.Map;

import Es_dnevniks.entities.MarkEntity;

public class TeacherMarksDTO {
	
	protected String subject;
	
	protected Map<String, List<MarkEntity>> studentMarks;

	public TeacherMarksDTO(String subject, Map<String, List<MarkEntity>> studentMarks) {
		super();
		this.subject = subject;
		this.studentMarks = studentMarks;
	}

	public TeacherMarksDTO() {
		super();
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Map<String, List<MarkEntity>> getStudentMarks() {
		return studentMarks;
	}

	public void setStudentMarks(Map<String, List<MarkEntity>> studentMarks) {
		this.studentMarks = studentMarks;
	}

	

}
