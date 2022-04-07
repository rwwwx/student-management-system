package com.example.annotationtest.utils;

import com.example.annotationtest.entityRepository.StudentRepo;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class EmailExistsValidator implements ConstraintValidator<EmailExists, String> {

    private final StudentRepo studentRepo;

    @Autowired
    public EmailExistsValidator(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public void initialize(EmailExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !studentRepo.existsByEmail(email);
    }

}
