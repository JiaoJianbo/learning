/**
 * 
 */
package com.bravo.demo.ssm.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author Bobby
 *
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {
	// 别忘了下面三个属性
	//String message() default "{org.hibernate.validator.constraints.NotBlank.message}";
	String message() default "MyConstraint default validation message.";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
