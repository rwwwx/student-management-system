package com.example.annotationtest.utils;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = EmailExistsValidator.class)
public @interface EmailExists {

    String message() default "{invalidEmailException}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
