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
@Table(name="marks")
public class StudentMark {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mark_id")
	protected Integer id;

	@ManyToOne(cascade= {CascadeType.ALL},fetch = FetchType.LAZY)
	@MapsId("mark")
	@JoinColumn(name = "marks_id")
	MarkEntity mark;
	
	@ManyToOne(cascade= {CascadeType.ALL},fetch = FetchType.LAZY)
	@MapsId("student")
	@JoinColumn(name = "student_id")
	StudentEntity student;

	public StudentMark(Integer id, MarkEntity mark, StudentEntity student) {
		super();
		this.id = id;
		this.mark = mark;
		this.student = student;
	}

	
	public StudentMark() {
		super();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MarkEntity getMark() {
		return mark;
	}

	public void setMark(MarkEntity mark) {
		this.mark = mark;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}
	
}
