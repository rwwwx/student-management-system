package com.example.annotationtest.utils;

import com.example.annotationtest.entityRepository.StudentRepo;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class EmailExistenceCheckValidator implements ConstraintValidator<EmailExistenceCheck, String> {

    private final StudentRepo studentRepo;

    @Autowired
    public EmailExistenceCheckValidator(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
        log.info("EmailAddressValidator init...");
    }

    @Override
    public void initialize(EmailExistenceCheck constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        log.info("Validating...");
        return !studentRepo.existsByEmail(email);
    }

}
