package Es_dnevniks.entities.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;




public class TeacherEntityDTO {
	

	@Column(nullable=false)
	@NotNull(message="Name must be provided")
	@Size(min=5,max=20, message= "Name must be beetwen {min} and {max} characters long.")
	protected String first_name;
	

	@Column(nullable=false)
	@NotNull(message="Laste name must be provided")
	@Size(min=5,max=20, message= "Last name must be beetwen {min} and {max} characters long.")
	protected String last_name;


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public TeacherEntityDTO() {
		super();
	}


	public TeacherEntityDTO(
			@NotNull(message = "Name must be provided") @Size(min = 5, max = 20, message = "Name must be beetwen {min} and {max} characters long.") String first_name,
			@NotNull(message = "Laste name must be provided") @Size(min = 5, max = 20, message = "Last name must be beetwen {min} and {max} characters long.") String last_name) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
	}

}
