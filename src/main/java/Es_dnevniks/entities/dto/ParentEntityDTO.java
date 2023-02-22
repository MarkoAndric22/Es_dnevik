package Es_dnevniks.entities.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;


public class ParentEntityDTO {
	
	@Column(nullable=false)
	@NotNull(message="First name must be provided")
	@Size(min=2,max=30, message= "First name must be beetwen {min} and {max} characters long.")
	protected String first_name;
	
	@Column(nullable=false)
	@NotNull(message="Last name must be provided")
	@Size(min=2,max=30, message= "Last name must be beetwen {min} and {max} characters long.")
	protected String last_name;
	
	@Column(nullable=false)
	@NotNull(message="Email must be provided")
	@Size(min=2,max=30, message= "Email must be beetwen {min} and {max} characters long.")
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
	message="Email is not valid.")
	protected String email;

	public ParentEntityDTO(
			@NotNull(message = "First name must be provided") @Size(min = 2, max = 30, message = "First name must be beetwen {min} and {max} characters long.") String first_name,
			@NotNull(message = "Last name must be provided") @Size(min = 2, max = 30, message = "Last name must be beetwen {min} and {max} characters long.") String last_name,
			@NotNull(message = "Email must be provided") @Size(min = 2, max = 30, message = "Email must be beetwen {min} and {max} characters long.") @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email is not valid.") String email) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
	}

	public ParentEntityDTO() {
		super();
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
