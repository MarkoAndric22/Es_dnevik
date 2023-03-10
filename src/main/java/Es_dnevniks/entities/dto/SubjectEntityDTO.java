package Es_dnevniks.entities.dto;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class SubjectEntityDTO {
	
	@Column(nullable=false,name="name_subject")
	@NotNull(message="Name must be provided")
	@Size(min=2,max=30, message= "Name must be beetwen {min} and {max} characters long.")
	protected String name;
	
	@Column(nullable=false,name="fond_classes")
	@NotNull(message="fond must be provided")
	@Max(value=40, message= "Max number class is 40")
	protected Integer fond;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getFond() {
		return fond;
	}
	public void setFond(Integer fond) {
		this.fond = fond;
	}
	
	public SubjectEntityDTO() {
		super();
	}
	public SubjectEntityDTO(
			@NotNull(message = "Name must be provided") @Size(min = 2, max = 30, message = "Name must be beetwen {min} and {max} characters long.") String name,
			@NotNull(message = "fond must be provided") @Max(value = 40, message = "Max number class is 40") Integer fond) {
		super();
		this.name = name;
		this.fond = fond;
	}


}
