package Es_dnevniks.utils;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import Es_dnevniks.entities.dto.UserEntityDTO;


public class UserCustomValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserEntityDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserEntityDTO user=(UserEntityDTO) target;
		if(!user.getPassword().equals(user.getRepeatedPassword())) {
			errors.reject("400","Passwords must be the same");
		}
		
	}

}
