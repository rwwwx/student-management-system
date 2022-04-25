package com.example.annotationtest.utils;

import com.example.annotationtest.entityRepository.StudentRepo;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class EmailExistsValidatorForStudent implements ConstraintValidator<EmailExistsForStudent, String> {

    private final StudentRepo studentRepo;

    public EmailExistsValidatorForStudent(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public void initialize(EmailExistsForStudent constraintAnnotation) {
        log.info("INIT ");
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return validation(email);
    }

    public boolean validation(String email) {
        return !studentRepo.existsByEmail(email);
    }

}
