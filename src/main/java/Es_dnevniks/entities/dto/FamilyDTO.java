package Es_dnevniks.entities.dto;

public class FamilyDTO {
	
	protected String parentName;
	
	protected String studentName;

	public FamilyDTO(String parentName, String studentName) {
		super();
		this.parentName = parentName;
		this.studentName = studentName;
	}

	public FamilyDTO() {
		super();
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	

}
