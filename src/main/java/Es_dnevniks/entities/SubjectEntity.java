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
	
	@Column(nullable=false,name="name_subject")
	@NotNull(message="Name must be provided")
	@Size(min=2,max=30, message= "Name must be beetwen {min} and {max} characters long.")
	protected String name;
	
	@Column(nullable=false,name="fond_classes")
	@NotNull(message="fond must be provided")
	@Max(value=40, message= "Max number class is 40")
	protected Integer fond;
	
	@NotNull(message="semester must be provided")
	protected Semester semester;
	
	
	@OneToMany (mappedBy="subject", cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JsonIgnore
	List <NasPredajuPred>nasPredajuPreds;
	
	@OneToMany(mappedBy="subject", cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JsonIgnore
	List <StudentSubject>studentSubject;
	
	@OneToMany(mappedBy="subject", cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JsonIgnore
	List <SubjectClass>subjectClass;
	
	@OneToMany(mappedBy="subject", cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JsonIgnore
	List <MarkSubject>markSubject;

	@OneToMany(mappedBy="subject", cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
	@JsonIgnore
	List <StudentSubjectMark> studentSubjectMark;
	
	
	

	public SubjectEntity(Integer id,
			@NotNull(message = "Name must be provided") @Size(min = 2, max = 30, message = "Name must be beetwen {min} and {max} characters long.") String name,
			@NotNull(message = "fond must be provided") @Max(value = 40, message = "Max number class is 40") Integer fond,
			@NotNull(message = "semester must be provided") Semester semester, List<NasPredajuPred> nasPredajuPreds,
			List<StudentSubject> studentSubject, List<SubjectClass> subjectClass, List<MarkSubject> markSubject,
			List<StudentSubjectMark> studentSubjectMark) {
		super();
		this.id = id;
		this.name = name;
		this.fond = fond;
		this.semester = semester;
		this.nasPredajuPreds = nasPredajuPreds;
		this.studentSubject = studentSubject;
		this.subjectClass = subjectClass;
		this.markSubject = markSubject;
		this.studentSubjectMark = studentSubjectMark;
	}

	public List<StudentSubjectMark> getStudentSubjectMark() {
		return studentSubjectMark;
	}

	public void setStudentSubjectMark(List<StudentSubjectMark> studentSubjectMark) {
		this.studentSubjectMark = studentSubjectMark;
	}

	public List<MarkSubject> getMarkSubject() {
		return markSubject;
	}

	public void setMarkSubject(List<MarkSubject> markSubject) {
		this.markSubject = markSubject;
	}

	public SubjectEntity(Integer id,
			@NotNull(message = "Name must be provided") @Size(min = 2, max = 30, message = "Name must be beetwen {min} and {max} characters long.") String name,
			@NotNull(message = "fond must be provided") @Max(value = 40, message = "Max number class is 40") Integer fond,
			@NotNull(message = "semester must be provided") Semester semester, List<NasPredajuPred> nasPredajuPreds,
			List<StudentSubject> studentSubject, List<SubjectClass> subjectClass) {
		super();
		this.id = id;
		this.name = name;
		this.fond = fond;
		this.semester = semester;
		this.nasPredajuPreds = nasPredajuPreds;
		this.studentSubject = studentSubject;
		this.subjectClass = subjectClass;
	}

	public SubjectEntity(
			@NotNull(message = "Name must be provided") @Size(min = 2, max = 30, message = "Name must be beetwen {min} and {max} characters long.") String name,
			@NotNull(message = "fond must be provided") @Max(value = 40, message = "Max number class is 40") Integer fond,
			@NotNull(message = "semester must be provided") Semester semester, List<NasPredajuPred> nasPredajuPreds,
			List<StudentSubject> studentSubject, List<SubjectClass> subjectClass) {
		super();
		this.name = name;
		this.fond = fond;
		this.semester = semester;
		this.nasPredajuPreds = nasPredajuPreds;
		this.studentSubject = studentSubject;
		this.subjectClass = subjectClass;
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

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
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

	public List<SubjectClass> getSubjectClass() {
		return subjectClass;
	}

	public void setSubjectClass(List<SubjectClass> subjectClass) {
		this.subjectClass = subjectClass;
	}

	public SubjectEntity(
			@NotNull(message = "Name must be provided") @Size(min = 2, max = 30, message = "Name must be beetwen {min} and {max} characters long.") String name,
			@NotNull(message = "fond must be provided") @Max(value = 40, message = "Max number class is 40") Integer fond,
			@NotNull(message = "semester must be provided") Semester semester) {
		super();
		this.name = name;
		this.fond = fond;
		this.semester = semester;
	}

	
	
}
