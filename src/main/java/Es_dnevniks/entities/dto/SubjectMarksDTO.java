package Es_dnevniks.entities.dto;

import java.util.List;

import Es_dnevniks.entities.MarkEntity;

public class SubjectMarksDTO {
	
	protected String subject;
	
	protected List<MarkEntity>marks;

	public SubjectMarksDTO(String subject, List<MarkEntity> marks) {
		super();
		this.subject = subject;
		this.marks = marks;
	}

	public SubjectMarksDTO() {
		super();
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<MarkEntity> getMarks() {
		return marks;
	}

	public void setMarks(List<MarkEntity> marks) {
		this.marks = marks;
	}
	
	

}
