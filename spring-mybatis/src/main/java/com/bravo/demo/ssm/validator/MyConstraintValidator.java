package com.bravo.demo.ssm.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		System.out.println("MyConstraintValidator init ... ...");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		System.out.println("MyConstraintValidator validation pass.");
		return true;
	}

}
