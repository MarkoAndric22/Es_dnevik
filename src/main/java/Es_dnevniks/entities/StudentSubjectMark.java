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
@Table(name="studentSubjectMark")
public class StudentSubjectMark {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Integer id;
	
	@ManyToOne(cascade= {CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	protected StudentEntity student;
	
	@ManyToOne(cascade= {CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id")
	protected SubjectEntity subject;
	
	@ManyToOne(cascade= {CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name = "mark_id")
	protected MarkEntity mark;

	public StudentSubjectMark(Integer id, StudentEntity student, SubjectEntity subject, MarkEntity mark) {
		super();
		this.id = id;
		this.student = student;
		this.subject = subject;
		this.mark = mark;
	}
	
	

	public StudentSubjectMark(StudentEntity student, SubjectEntity subject, MarkEntity mark) {
		super();
		this.student = student;
		this.subject = subject;
		this.mark = mark;
	}



	public StudentSubjectMark() {
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

	public MarkEntity getMark() {
		return mark;
	}

	public void setMark(MarkEntity mark) {
		this.mark = mark;
	}
	
	

}
