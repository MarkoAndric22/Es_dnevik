package Es_dnevniks.entities.dto;

import java.util.List;

import Es_dnevniks.entities.MarkEntity;

public class ListMarksDTO {
	
	protected String name;
	
	protected List<SubjectMarksDTO>subjectMarks;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SubjectMarksDTO> getSubjectMarks() {
		return subjectMarks;
	}

	public void setSubjectMarks(List<SubjectMarksDTO> subjectMarks) {
		this.subjectMarks = subjectMarks;
	}

	public ListMarksDTO(String name, List<SubjectMarksDTO> subjectMarks) {
		super();
		this.name = name;
		this.subjectMarks = subjectMarks;
	}

	public ListMarksDTO() {
		super();
	}

	
}
