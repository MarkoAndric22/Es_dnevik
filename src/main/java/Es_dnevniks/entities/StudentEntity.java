package Es_dnevniks.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;




@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class StudentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Integer id;
	@JsonProperty("first_name")
	@Column(nullable=false,name="first_name")
	@NotNull(message="First name must be provided")
	@Size(min=2,max=30, message= "First name must be beetwen {min} and {max} characters long.")
	protected String first_name;
	@JsonProperty("last_name")
	@Column(nullable=false,name="last_name")
	@NotNull(message="Last name must be provided")
	@Size(min=2,max=30, message= "Last name must be beetwen {min} and {max} characters long.")
	protected String last_name;
	
	@OneToMany(mappedBy="student", cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
	@JsonIgnore
	List <StudentSubject>studentSubj;
	
	
	@OneToMany(mappedBy="student", cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
	@JsonIgnore
	List <StudentSubjectMark> studentSubjectMark;
	@JsonIgnore
	@ManyToOne(cascade= {CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name = "classes_id", nullable = true)
	ClassEntity classes;
	
	@OneToMany(mappedBy="student", cascade = {CascadeType.MERGE},fetch = FetchType.LAZY)
	@JsonIgnore
	List <ParentStudent>parentStudents;

	@OneToOne(cascade= {CascadeType.REFRESH},fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user")
	@JsonIgnore
	UserEntity user;
	

	public StudentEntity(Integer id,
			@NotNull(message = "First name must be provided") @Size(min = 2, max = 30, message = "First name must be beetwen {min} and {max} characters long.") String first_name,
			@NotNull(message = "Last name must be provided") @Size(min = 2, max = 30, message = "Last name must be beetwen {min} and {max} characters long.") String last_name) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
	}

	public StudentEntity(Integer id,
			@NotNull(message = "First name must be provided") @Size(min = 2, max = 30, message = "First name must be beetwen {min} and {max} characters long.") String first_name,
			@NotNull(message = "Last name must be provided") @Size(min = 2, max = 30, message = "Last name must be beetwen {min} and {max} characters long.") String last_name,
			List<StudentSubject> studentSubj, ClassEntity classes, List<ParentStudent> parentStudents,
			UserEntity user) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.studentSubj = studentSubj;
		this.classes = classes;
		this.parentStudents = parentStudents;
		this.user = user;
	}

	public StudentEntity(
			@NotNull(message = "First name must be provided") @Size(min = 2, max = 30, message = "First name must be beetwen {min} and {max} characters long.") String first_name,
			@NotNull(message = "Last name must be provided") @Size(min = 2, max = 30, message = "Last name must be beetwen {min} and {max} characters long.") String last_name,
			List<StudentSubject> studentSubj, ClassEntity classes, List<ParentStudent> parentStudents,
			UserEntity user) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.studentSubj = studentSubj;
		this.classes = classes;
		this.parentStudents = parentStudents;
		this.user = user;
	}

	public StudentEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<StudentSubject> getStudentSubj() {
		return studentSubj;
	}

	public void setStudentSubj(List<StudentSubject> studentSubj) {
		this.studentSubj = studentSubj;
	}

	public ClassEntity getClasses() {
		return classes;
	}

	public void setClasses(ClassEntity classes) {
		this.classes = classes;
	}

	public List<ParentStudent> getParentStudents() {
		return parentStudents;
	}

	public void setParentStudents(List<ParentStudent> parentStudents) {
		this.parentStudents = parentStudents;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public StudentEntity(
			@NotNull(message = "First name must be provided") @Size(min = 2, max = 30, message = "First name must be beetwen {min} and {max} characters long.") String first_name,
			@NotNull(message = "Last name must be provided") @Size(min = 2, max = 30, message = "Last name must be beetwen {min} and {max} characters long.") String last_name) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
	}

	
	

	public List<StudentSubjectMark> getStudentSubjectMark() {
		return studentSubjectMark;
	}

	public void setStudentSubjectMark(List<StudentSubjectMark> studentSubjectMark) {
		this.studentSubjectMark = studentSubjectMark;
	}

	@Override
	public String toString() {
		return "StudentEntity [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + "]";
	}

	
	


	
	
}
