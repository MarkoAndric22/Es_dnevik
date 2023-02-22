package Es_dnevniks.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;






@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class TeacherEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Integer id;
	

	@Column(nullable=false)
	@NotNull(message="Name must be provided")
	@Size(min=5,max=20, message= "Name must be beetwen {min} and {max} characters long.")
	protected String first_name;
	

	@Column(nullable=false)
	@NotNull(message="Laste name must be provided")
	@Size(min=5,max=20, message= "Last name must be beetwen {min} and {max} characters long.")
	protected String last_name;
	
	@OneToMany(mappedBy="teacher", cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JsonIgnore
	List <NasPredajuPred>nasPredajuPreds;
	
	@OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
	UserEntity user;

	public TeacherEntity(Integer id,
			@NotNull(message = "Name must be provided") @Size(min = 5, max = 20, message = "Name must be beetwen {min} and {max} characters long.") String first_name,
			@NotNull(message = "Laste name must be provided") @Size(min = 5, max = 20, message = "Last name must be beetwen {min} and {max} characters long.") String last_name,
			List<NasPredajuPred> nasPredajuPreds, UserEntity user) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.nasPredajuPreds = nasPredajuPreds;
		this.user = user;
	}

	public TeacherEntity(
			@NotNull(message = "Name must be provided") @Size(min = 5, max = 20, message = "Name must be beetwen {min} and {max} characters long.") String first_name,
			@NotNull(message = "Laste name must be provided") @Size(min = 5, max = 20, message = "Last name must be beetwen {min} and {max} characters long.") String last_name,
			List<NasPredajuPred> nasPredajuPreds, UserEntity user) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.nasPredajuPreds = nasPredajuPreds;
		this.user = user;
	}

	public TeacherEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public List<NasPredajuPred> getNasPredajuPreds() {
		return nasPredajuPreds;
	}

	public void setNasPredajuPreds(List<NasPredajuPred> nasPredajuPreds) {
		this.nasPredajuPreds = nasPredajuPreds;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public TeacherEntity(
			@NotNull(message = "Name must be provided") @Size(min = 5, max = 20, message = "Name must be beetwen {min} and {max} characters long.") String first_name,
			@NotNull(message = "Laste name must be provided") @Size(min = 5, max = 20, message = "Last name must be beetwen {min} and {max} characters long.") String last_name) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
	}
	
	
}
