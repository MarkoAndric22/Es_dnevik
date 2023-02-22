package Es_dnevniks.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;






@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class MarkEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Integer id;
	
	MarkEnum marks;
	
	@OneToMany(mappedBy="mark", cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JsonIgnore
	List <StudentMark>studentMarks;
	
	@OneToMany(mappedBy="mark", cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JsonIgnore
	List <MarkSubject>markSubject;

	@OneToMany(mappedBy="mark", cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
	@JsonIgnore
	List <StudentSubjectMark> studentSubjectMark;
	
	

	public MarkEntity(Integer id, MarkEnum marks, List<StudentMark> studentMarks, List<MarkSubject> markSubject,
			List<StudentSubjectMark> studentSubjectMark) {
		super();
		this.id = id;
		this.marks = marks;
		this.studentMarks = studentMarks;
		this.markSubject = markSubject;
		this.studentSubjectMark = studentSubjectMark;
	}

	public List<MarkSubject> getMarkSubject() {
		return markSubject;
	}

	public void setMarkSubject(List<MarkSubject> markSubject) {
		this.markSubject = markSubject;
	}

	public List<StudentSubjectMark> getStudentSubjectMark() {
		return studentSubjectMark;
	}

	public void setStudentSubjectMark(List<StudentSubjectMark> studentSubjectMark) {
		this.studentSubjectMark = studentSubjectMark;
	}

	public MarkEntity(Integer id, MarkEnum marks, List<StudentMark> studentMarks) {
		super();
		this.id = id;
		this.marks = marks;
		this.studentMarks = studentMarks;
	}

	public MarkEntity(MarkEnum marks, List<StudentMark> studentMarks) {
		super();
		this.marks = marks;
		this.studentMarks = studentMarks;
	}

	public MarkEntity() {
		super();
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

	public List<StudentMark> getStudentMarks() {
		return studentMarks;
	}

	public void setStudentMarks(List<StudentMark> studentMarks) {
		this.studentMarks = studentMarks;
	}
	
	@Override
	public String toString() {
		return "MarkEntity [id=" + id + ", marks=" + marks + ", studentMarks=" + studentMarks + ", markSubject="
				+ markSubject + "]";
	}

}
