package Es_dnevniks.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;





@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ClassEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Integer id;
	
	@Column(nullable=false)
	@NotNull(message="Name must be provided")
	@Size(min=2,max=30, message= "Name must be beetwen {min} and {max} characters long.")
	protected String name;
	
	@Column(nullable=false)
	@NotNull(message="Name must be provided")
	protected Semester semester;
	
	@OneToMany(mappedBy="classes", cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JsonIgnore
	List <SubjectClass>subjectClass;
	
	@OneToMany(mappedBy="classes", cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JsonIgnore
	List <StudentEntity>student;

	public ClassEntity(Integer id,
			@NotNull(message = "Name must be provided") @Size(min = 2, max = 30, message = "Name must be beetwen {min} and {max} characters long.") String name,
			@NotNull(message = "Name must be provided") Semester semester, List<SubjectClass> subjectClass,
			List<StudentEntity> student) {
		super();
		this.id = id;
		this.name = name;
		this.semester = semester;
		this.subjectClass = subjectClass;
		this.student = student;
	}

	public ClassEntity(
			@NotNull(message = "Name must be provided") @Size(min = 2, max = 30, message = "Name must be beetwen {min} and {max} characters long.") String name,
			@NotNull(message = "Name must be provided") Semester semester, List<SubjectClass> subjectClass,
			List<StudentEntity> student) {
		super();
		this.name = name;
		this.semester = semester;
		this.subjectClass = subjectClass;
		this.student = student;
	}

	public ClassEntity(
			@NotNull(message = "Name must be provided") @Size(min = 2, max = 30, message = "Name must be beetwen {min} and {max} characters long.") String name,
			@NotNull(message = "Name must be provided") Semester semester) {
		super();
		this.name = name;
		this.semester = semester;
	}

	public ClassEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<SubjectClass> getSubjectClass() {
		return subjectClass;
	}

	public void setSubjectClass(List<SubjectClass> subjectClass) {
		this.subjectClass = subjectClass;
	}

	public List<StudentEntity> getStudent() {
		return student;
	}

	public void setStudent(List<StudentEntity> student) {
		this.student = student;
	}

	
}
