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
@Table(name="ParentStudent")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ParentStudent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Integer id;
	
	@ManyToOne(cascade= {CascadeType.REFRESH},fetch = FetchType.LAZY)

	@JoinColumn(name = "parent_id")
	ParentEntity parent;
	
	@ManyToOne(cascade= {CascadeType.REFRESH},fetch = FetchType.LAZY)

	@JoinColumn(name = "student_id")
	StudentEntity student;

	public ParentStudent(Integer id, ParentEntity parent, StudentEntity student) {
		super();
		this.id = id;
		this.parent = parent;
		this.student = student;
	}
	
	

	public ParentStudent(ParentEntity parent, StudentEntity student) {
		super();
		this.parent = parent;
		this.student = student;
	}



	public ParentStudent() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ParentEntity getParent() {
		return parent;
	}

	public void setParent(ParentEntity parent) {
		this.parent = parent;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}
	
}
