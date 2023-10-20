package Es_dnevniks.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MarkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

//    @Enumerated(EnumType.ORDINAL) // Koristi ORDINAL za mapiranje na brojeve
    protected MarkEnum marks;

	@OneToMany(mappedBy="mark", cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JsonIgnore
	List <StudentSubjectMark> studentSubjectMark;
	
	public List<StudentSubjectMark> getStudentSubjectMark() {
		return studentSubjectMark;
	}

	public void setStudentSubjectMark(List<StudentSubjectMark> studentSubjectMark) {
		this.studentSubjectMark = studentSubjectMark;
	}

	public MarkEntity() {
		super();
	}

	public MarkEntity(Integer id, MarkEnum marks, List<StudentSubjectMark> studentSubjectMark) {
		super();
		this.id = id;
		this.marks = marks;
		this.studentSubjectMark = studentSubjectMark;
	}

	public MarkEntity(MarkEnum marks) {
		super();
		this.marks = marks;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MarkEnum getMarks() {
		return marks;
	}

	public void setMarks(MarkEnum marks) {
		this.marks = marks;
	}


}
