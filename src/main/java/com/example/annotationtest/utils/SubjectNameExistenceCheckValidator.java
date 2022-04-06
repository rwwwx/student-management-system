package com.example.annotationtest.utils;

import com.example.annotationtest.entityRepository.SubjectRepo;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class SubjectNameExistenceCheckValidator implements ConstraintValidator<SubjectNameExistenceCheck, String> {

    private final SubjectRepo subjectRepo;

    @Autowired
    public SubjectNameExistenceCheckValidator(SubjectRepo subjectRepo) {
        this.subjectRepo = subjectRepo;
        log.info("EmailAddressValidator init...");
    }

    @Override
    public void initialize(SubjectNameExistenceCheck constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        log.info("Validating...");
        return !subjectRepo.existsByName(name);
    }

}
