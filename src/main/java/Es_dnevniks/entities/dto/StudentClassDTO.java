package Es_dnevniks.entities.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StudentClassDTO {
	
	@Column(nullable=false,name="name_student")
	@NotNull(message="First name must be provided")
	@Size(min=2,max=30, message= "First name must be beetwen {min} and {max} characters long.")
	protected String first_name;
	
	@Column(nullable=false,name="Last_name_student")
	@NotNull(message="Last name must be provided")
	@Size(min=2,max=30, message= "Last name must be beetwen {min} and {max} characters long.")
	protected String last_name;
	
	
	@Column(nullable=false)
	@NotNull(message="Name must be provided")
	@Size(min=2,max=30, message= "Name must be beetwen {min} and {max} characters long.")
	protected String name;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StudentClassDTO(
			@NotNull(message = "First name must be provided") @Size(min = 2, max = 30, message = "First name must be beetwen {min} and {max} characters long.") String first_name,
			@NotNull(message = "Last name must be provided") @Size(min = 2, max = 30, message = "Last name must be beetwen {min} and {max} characters long.") String last_name,
			@NotNull(message = "Name must be provided") @Size(min = 2, max = 30, message = "Name must be beetwen {min} and {max} characters long.") String name) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.name = name;
	}

	public StudentClassDTO() {
		super();
	}
	
	
	

}
