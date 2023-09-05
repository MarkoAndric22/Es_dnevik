package Es_dnevniks.entities.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import Es_dnevniks.entities.StudentEntity;

public class ParentStudentDTO {
	@Column(nullable=false, unique = true)
	@NotNull(message="username must be provided")
	@Size(min=5,max=20, message= "username must be beetwen {min} and {max} characters long.")
	protected String username;
	
	@Column(nullable=false)
	@NotNull(message="First name must be provided")
	@Size(min=2,max=30, message= "First name must be beetwen {min} and {max} characters long.")
	protected String first_name;
	
	@Column(nullable=false)
	@NotNull(message="Last name must be provided")
	@Size(min=2,max=30, message= "Last name must be beetwen {min} and {max} characters long.")
	protected String lastName;
	
	@Column(nullable=false,name = "email", unique = true)
	@NotNull(message="Email must be provided")
	@Size(min=2,max=30, message= "Email must be beetwen {min} and {max} characters long.")
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
	message="Email is not valid.")
	protected String email;
	
	@Column(nullable=false)
	@NotNull(message="Password must be provided")
	@Size(min =5, message= "Password must have 5 or more letters")
	protected String password;
	
	protected String repeatedPassword;

	protected List<StudentEntity>students;

	public ParentStudentDTO() {
		super();
	}

	public ParentStudentDTO(
			@NotNull(message = "username must be provided") @Size(min = 5, max = 20, message = "username must be beetwen {min} and {max} characters long.") String username,
			@NotNull(message = "First name must be provided") @Size(min = 2, max = 30, message = "First name must be beetwen {min} and {max} characters long.") String first_name,
			@NotNull(message = "Last name must be provided") @Size(min = 2, max = 30, message = "Last name must be beetwen {min} and {max} characters long.") String lastName,
			@NotNull(message = "Email must be provided") @Size(min = 2, max = 30, message = "Email must be beetwen {min} and {max} characters long.") @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email is not valid.") String email,
			@NotNull(message = "Password must be provided") @Size(min = 5, message = "Password must have 5 or more letters") String password,
			String repeatedPassword, List<StudentEntity> students) {
		super();
		this.username = username;
		this.first_name = first_name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.repeatedPassword = repeatedPassword;
		this.students = students;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	public List<StudentEntity> getStudents() {
		return students;
	}

	public void setStudents(List<StudentEntity> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "ParentStudentDTO [username=" + username + ", first_name=" + first_name + ", lastName=" + lastName
				+ ", email=" + email + ", password=" + password + ", repeatedPassword=" + repeatedPassword
				+ ", students=" + students + "]";
	}

	
	

}
