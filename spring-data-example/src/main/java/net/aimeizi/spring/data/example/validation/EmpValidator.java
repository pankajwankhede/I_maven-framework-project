package net.aimeizi.spring.data.example.validation;

import net.aimeizi.spring.data.example.entities.Emp;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EmpValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Emp.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "empname", "emp.empname.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "empaddress", "emp.empaddress.empty");
	}

}
