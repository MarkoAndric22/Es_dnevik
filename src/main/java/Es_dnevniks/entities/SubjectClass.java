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
@Table(name="subject_in_class")
public class SubjectClass {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "subjectes_id")
	protected Integer id;	
	
	@ManyToOne(cascade= {CascadeType.ALL},fetch = FetchType.LAZY)
	@MapsId("subject")
	@JoinColumn(name = "subject_id")
	SubjectEntity subject;
	
	@ManyToOne(cascade= {CascadeType.ALL},fetch = FetchType.LAZY)
	@MapsId("classes")
	@JoinColumn(name = "classes_id")
	ClassEntity classes;
	
}
