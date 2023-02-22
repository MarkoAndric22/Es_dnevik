package Es_dnevniks.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
@Entity
@Table(name="predaju")
public class NasPredajuPred {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "predaju_id")
	protected Integer id;
	
	@ManyToOne(cascade= {CascadeType.ALL},fetch = FetchType.LAZY)
	@MapsId("teacher")
	@JoinColumn(name = "teacher_id")
	TeacherEntity teacher;
	
	@ManyToOne(cascade= {CascadeType.ALL},fetch = FetchType.LAZY)
	@MapsId("subject")
	@JoinColumn(name = "subject_id")
	SubjectEntity subject;

}
