package com.project.ecommerce.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OnlyGMailConstraintValidator implements ConstraintValidator<OnlyGMail, String> {
	
	private String suffix;
	
	@Override
	public void initialize(OnlyGMail onlyGMail) {
		suffix = onlyGMail.value();
	}

	@Override
	public boolean isValid(String code, ConstraintValidatorContext context) {
		if(code == null)
			return true;
		return code.endsWith(suffix);
	}

}
