package com.example.annotationtest.controller;

import com.example.annotationtest.exception.InvalidAccessException;
import com.example.annotationtest.exception.InvalidEmailException;
import com.example.annotationtest.exception.InvalidIdException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    private static final String MASSAGE_FOR_LOGGER = "ErrorHandlingControllerAdvice catch an exception ";

    public ErrorHandlingControllerAdvice() {
        log.info("ErrorHandlingControllerAdvice init");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException e) {
        log.error(MASSAGE_FOR_LOGGER, e);
        return new ResponseEntity<>(e. getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<String> handleInvalidEmailException(InvalidEmailException e) {
        log.error(MASSAGE_FOR_LOGGER, e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        log.error(MASSAGE_FOR_LOGGER, e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<String> handleInvalidIdException(InvalidIdException e) {
        log.error(MASSAGE_FOR_LOGGER, e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException e) {
        log.error(MASSAGE_FOR_LOGGER, e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidAccessException.class)
    public ResponseEntity<String> handleInvalidAccessException(InvalidAccessException e) {
        log.error(MASSAGE_FOR_LOGGER, e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

}
