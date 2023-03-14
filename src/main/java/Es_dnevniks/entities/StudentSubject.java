package Es_dnevniks.entities;

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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="subjectForStudent")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class StudentSubject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "subjectSid")
	protected Integer id;
	
	@ManyToOne(cascade= {CascadeType.REFRESH},fetch = FetchType.LAZY)

	@JoinColumn(name = "student_id")
	StudentEntity student;
	
	@ManyToOne(cascade= {CascadeType.REFRESH},fetch = FetchType.LAZY)

	@JoinColumn(name = "subject_id")
	SubjectEntity subject;

	public StudentSubject(Integer id, StudentEntity student, SubjectEntity subject) {
		super();
		this.id = id;
		this.student = student;
		this.subject = subject;
	}
	
	

	public StudentSubject() {
		super();
	}

	public StudentSubject(StudentEntity student, SubjectEntity subject) {
		super();
		this.student = student;
		this.subject = subject;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}

	public SubjectEntity getSubject() {
		return subject;
	}

	public void setSubject(SubjectEntity subject) {
		this.subject = subject;
	}
	

}
