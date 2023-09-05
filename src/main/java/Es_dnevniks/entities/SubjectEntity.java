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
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class SubjectEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Integer id;
	
	@Column(nullable=false,name="name")
	@NotNull(message="Name must be provided")
	@Size(min=2,max=30, message= "Name must be beetwen {min} and {max} characters long.")
	protected String name;
	
	@Column(nullable=false,name="fond_classes")
	@NotNull(message="fond must be provided")
	@Max(value=40, message= "Max number class is 40")
	protected Integer fond;
	
	
	@OneToMany (mappedBy="subject", cascade = {CascadeType.REFRESH, CascadeType.REMOVE},fetch = FetchType.LAZY)
	@JsonIgnore
	List <NasPredajuPred>nasPredajuPreds;
	
	@OneToMany(mappedBy="subject", cascade = {CascadeType.REFRESH, CascadeType.REMOVE},fetch = FetchType.LAZY)
	@JsonIgnore
	List <StudentSubject>studentSubject;


	@OneToMany(mappedBy="subject", cascade = {CascadeType.REFRESH, CascadeType.REMOVE},fetch = FetchType.LAZY)
	@JsonIgnore
	List <StudentSubjectMark> studentSubjectMark;


	public SubjectEntity(Integer id,
			@NotNull(message = "Name must be provided") @Size(min = 2, max = 30, message = "Name must be beetwen {min} and {max} characters long.") String name,
			@NotNull(message = "fond must be provided") @Max(value = 40, message = "Max number class is 40") Integer fond,
			List<NasPredajuPred> nasPredajuPreds, List<StudentSubject> studentSubject,
			List<StudentSubjectMark> studentSubjectMark) {
		super();
		this.id = id;
		this.name = name;
		this.fond = fond;
		this.nasPredajuPreds = nasPredajuPreds;
		this.studentSubject = studentSubject;
		this.studentSubjectMark = studentSubjectMark;
	}



	public SubjectEntity(
			@NotNull(message = "Name must be provided") @Size(min = 2, max = 30, message = "Name must be beetwen {min} and {max} characters long.") String name,
			@NotNull(message = "fond must be provided") @Max(value = 40, message = "Max number class is 40") Integer fond) {
		super();
		this.name = name;
		this.fond = fond;
	}



	public List<StudentSubjectMark> getStudentSubjectMark() {
		return studentSubjectMark;
	}

	public void setStudentSubjectMark(List<StudentSubjectMark> studentSubjectMark) {
		this.studentSubjectMark = studentSubjectMark;
	}


	public SubjectEntity() {
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

	public Integer getFond() {
		return fond;
	}

	public void setFond(Integer fond) {
		this.fond = fond;
	}

	public List<NasPredajuPred> getNasPredajuPreds() {
		return nasPredajuPreds;
	}

	public void setNasPredajuPreds(List<NasPredajuPred> nasPredajuPreds) {
		this.nasPredajuPreds = nasPredajuPreds;
	}

	public List<StudentSubject> getStudentSubject() {
		return studentSubject;
	}

	public void setStudentSubject(List<StudentSubject> studentSubject) {
		this.studentSubject = studentSubject;
	}

	
	
}
