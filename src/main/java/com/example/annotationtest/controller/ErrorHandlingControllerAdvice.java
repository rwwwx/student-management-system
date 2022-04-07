package com.example.annotationtest.controller;

import com.example.annotationtest.exception.InvalidEmailException;
import com.example.annotationtest.exception.InvalidIdException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    public ErrorHandlingControllerAdvice() {
        log.info("ErrorHandlingControllerAdvice init");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException e) {
        log.error("ErrorHandlingControllerAdvice catch an exception", e);
        return new ResponseEntity<>(e. getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<String> handleInvalidEmailException(InvalidEmailException e) {
        log.error("ErrorHandlingControllerAdvice catch an exception ", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        log.error("ErrorHandlingControllerAdvice catch an exception ", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<String> handleInvalidIdException(InvalidIdException e) {
        log.error("ErrorHandlingControllerAdvice catch an exception ", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
