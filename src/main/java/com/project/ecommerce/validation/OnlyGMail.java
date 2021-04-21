package com.project.ecommerce.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = OnlyGMailConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OnlyGMail {
	String value() default "gmail.com";
	String message() default "Only GMails are allowed";
	Class<?>[] groups() default  {};
	Class<? extends Payload>[] payload() default {};
}
