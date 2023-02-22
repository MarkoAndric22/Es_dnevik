package Es_dnevniks.entities.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import Es_dnevniks.entities.Semester;


public class ClassEntityDTO {
	
	@Column(nullable=false)
	@NotNull(message="Name must be provided")
	@Size(min=2,max=30, message= "Name must be beetwen {min} and {max} characters long.")
	protected String name;
	
	@Column(nullable=false)
	@NotNull(message="Name must be provided")
	protected Semester semester;

	public ClassEntityDTO(
			@NotNull(message = "Name must be provided") @Size(min = 2, max = 30, message = "Name must be beetwen {min} and {max} characters long.") String name,
			@NotNull(message = "Name must be provided") Semester semester) {
		super();
		this.name = name;
		this.semester = semester;
	}

	public ClassEntityDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

}
