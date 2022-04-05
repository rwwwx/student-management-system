package com.example.annotationtest.controller;

import com.example.annotationtest.exception.invalidEmailException;
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
        log.error("ErrorHandlingControllerAdvice catch an exception");
        return new ResponseEntity<>(e. getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(invalidEmailException.class)
    public ResponseEntity<String> handleInvalidEmailException(invalidEmailException e) {
        log.error("ErrorHandlingControllerAdvice catch an exception " + e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> listOfStudentsIsEmptyException(HttpMessageNotReadableException e) {
        log.error("ErrorHandlingControllerAdvice catch an exception " + e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> listOfStudentsIsEmptyException(ConstraintViolationException e) {
        log.error("ErrorHandlingControllerAdvice catch an exception " + e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}