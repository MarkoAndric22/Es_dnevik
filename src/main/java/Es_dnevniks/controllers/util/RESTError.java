package Es_dnevniks.controllers.util;

import javax.swing.text.View;

import com.fasterxml.jackson.annotation.JsonView;




public class RESTError extends Exception {
	
	
	private Integer code;
	
	private String message;
	public RESTError(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public RESTError() {
		super();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
