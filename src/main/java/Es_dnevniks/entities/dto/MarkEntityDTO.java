package Es_dnevniks.entities.dto;



import Es_dnevniks.entities.MarkEnum;



public class MarkEntityDTO {
	

	MarkEnum marks;

	public MarkEntityDTO() {
		super();
	}

	public MarkEnum getMarks() {
		return marks;
	}

	public void setMarks(MarkEnum marks) {
		this.marks = marks;
	}

	public MarkEntityDTO(MarkEnum marks) {
		super();
		this.marks = marks;
	}

}
