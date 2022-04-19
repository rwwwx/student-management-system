package com.example.annotationtest.utils;

import com.example.annotationtest.entityRepository.UserRepo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailExistsValidatorForUser implements ConstraintValidator<EmailExistsForUser, String> {

    private final UserRepo userRepo;

    public EmailExistsValidatorForUser(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void initialize(EmailExistsForUser constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return validation(email);
    }

    public boolean validation(String email) {
        return !userRepo.existsByEmail(email);
    }

}
