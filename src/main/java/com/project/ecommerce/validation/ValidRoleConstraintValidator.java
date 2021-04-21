package com.project.ecommerce.validation;

import com.project.ecommerce.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidRoleConstraintValidator implements ConstraintValidator<ValidRole, String> {

    @Override
    public void initialize(ValidRole constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Autowired
    RoleRepository roleRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null){
            return true;
        }
        return roleRepository.existsById(value);
    }
}
