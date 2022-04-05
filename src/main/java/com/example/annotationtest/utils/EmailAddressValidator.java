package com.example.annotationtest.utils;

import com.example.annotationtest.entityRepository.StudentRepo;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class EmailAddressValidator implements ConstraintValidator<EmailValidation, String> {

    private final StudentRepo studentRepo;

    @Autowired
    public EmailAddressValidator(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
        log.info("EmailAddressValidator init...");
    }

    @Override
    public void initialize(EmailValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        log.info("Validating...");
        return !studentRepo.existsByEmail(email);
    }

}
