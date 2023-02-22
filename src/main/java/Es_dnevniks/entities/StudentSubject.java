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

@Entity
@Table(name="subjectForStudent")
public class StudentSubject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "subjectSid")
	protected Integer id;
	
	@ManyToOne(cascade= {CascadeType.ALL},fetch = FetchType.LAZY)
	@MapsId("student")
	@JoinColumn(name = "student_id")
	StudentEntity student;
	
	@ManyToOne(cascade= {CascadeType.ALL},fetch = FetchType.LAZY)
	@MapsId("subject")
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