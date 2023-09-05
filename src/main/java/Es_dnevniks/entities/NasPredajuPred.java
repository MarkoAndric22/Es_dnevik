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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name="TeacherSubject")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class NasPredajuPred {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "predaju_id")
	protected Integer id;
	
	@ManyToOne(cascade= {CascadeType.REFRESH},fetch = FetchType.LAZY)

	@JoinColumn(name = "teacher_id")
	TeacherEntity teacher;
	
	@ManyToOne(cascade= {CascadeType.REFRESH},fetch = FetchType.LAZY)

	@JoinColumn(name = "subject_id")
	SubjectEntity subject;

	public NasPredajuPred(Integer id, TeacherEntity teacher, SubjectEntity subject) {
		super();
		this.id = id;
		this.teacher = teacher;
		this.subject = subject;
	}

	public NasPredajuPred() {
		super();
	}

	public NasPredajuPred(TeacherEntity teacher, SubjectEntity subject) {
		super();
		this.teacher = teacher;
		this.subject = subject;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TeacherEntity getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherEntity teacher) {
		this.teacher = teacher;
	}

	public SubjectEntity getSubject() {
		return subject;
	}

	public void setSubject(SubjectEntity subject) {
		this.subject = subject;
	}
	
	
	
	

}
