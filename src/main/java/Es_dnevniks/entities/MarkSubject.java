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
@Table(name="markSubject")
public class MarkSubject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "family_id")
	protected Integer id;
	
	@ManyToOne(cascade= {CascadeType.ALL},fetch = FetchType.LAZY)
	@MapsId("mark")
	@JoinColumn(name = "mark_id")
	MarkEntity mark;
	
	@ManyToOne(cascade= {CascadeType.ALL},fetch = FetchType.LAZY)
	@MapsId("subject")
	@JoinColumn(name = "subject_id")
	SubjectEntity subject;

	public MarkSubject(Integer id, MarkEntity mark, SubjectEntity subject) {
		super();
		this.id = id;
		this.mark = mark;
		this.subject = subject;
	}

	public MarkSubject() {
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

	public SubjectEntity getSubject() {
		return subject;
	}

	public void setSubject(SubjectEntity subject) {
		this.subject = subject;
	}

	

}
