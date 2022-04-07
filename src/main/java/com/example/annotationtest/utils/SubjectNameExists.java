package com.example.annotationtest.utils;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = SubjectNameExistsValidator.class)
public @interface SubjectNameExists {

    String message() default "{invalidSubjectName}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
