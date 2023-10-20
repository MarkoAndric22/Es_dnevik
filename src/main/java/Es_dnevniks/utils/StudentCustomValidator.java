package Es_dnevniks.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import Es_dnevniks.entities.dto.StudentAddDto;

@Component
public class StudentCustomValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return StudentAddDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		StudentAddDto user=(StudentAddDto) target;
		if(!user.getPassword().equals(user.getRepeatedPassword())) {
			errors.reject("400","Passwords must be the same");
		}
		
	}
}
