package com.example.annotationtest.utils;

import com.example.annotationtest.entityRepository.SubjectRepo;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class SubjectNameExistsValidator implements ConstraintValidator<SubjectNameExists, String> {

    private final SubjectRepo subjectRepo;

    @Autowired
    public SubjectNameExistsValidator(SubjectRepo subjectRepo) {
        this.subjectRepo = subjectRepo;
    }

    @Override
    public void initialize(SubjectNameExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        log.info("Validating...");
        return !subjectRepo.existsByName(name);
    }

}
