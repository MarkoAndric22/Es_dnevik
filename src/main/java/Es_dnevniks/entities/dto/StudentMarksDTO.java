package Es_dnevniks.entities.dto;

import Es_dnevniks.entities.MarkEnum;

public class StudentMarksDTO {
	
	protected String student;
	
	protected String subject;
	
	protected MarkEnum mark;

	public StudentMarksDTO(String student, String subject, MarkEnum mark) {
		super();
		this.student = student;
		this.subject = subject;
		this.mark = mark;
	}

	public StudentMarksDTO() {
		super();
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public MarkEnum getMark() {
		return mark;
	}

	public void setMark(MarkEnum mark) {
		this.mark = mark;
	}
	
	

}
