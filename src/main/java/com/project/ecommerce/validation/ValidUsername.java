package com.project.ecommerce.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidUsernameValidatorConstraint.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUsername {
    String value() default "";
    String message() default "No matching username found";
    Class<?>[] groups() default  {};
    Class<? extends Payload>[] payload() default {};
}
