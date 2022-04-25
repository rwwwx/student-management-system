package com.example.annotationtest.controller;

import com.example.annotationtest.entity.Student;
import com.example.annotationtest.entity.Subject;
import com.example.annotationtest.entityRepository.StudentRepo;
import com.example.annotationtest.entityRepository.SubjectRepo;
import com.example.annotationtest.exception.InvalidEmailException;
import com.example.annotationtest.service.StudentService;
import com.example.annotationtest.service.SubjectService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(controllers = {SubjectController.class, SubjectController.class})
class ErrorHandlingControllerAdviceTest {

    @Autowired
    private ErrorHandlingControllerAdvice advice;

    @MockBean
    private SubjectService subjectService;
    @MockBean
    private StudentService studentService;

    @MockBean
    private SubjectRepo subjectRepo;
    @MockBean
    private StudentRepo studentRepo;

    @Autowired
    private MockMvc mockMvcSubject;
    @Autowired
    private MockMvc mockMvcStudent;

    private Subject validSubject;
    private Subject invalidSubject;

    private Student validStudent;
    private Student invalidStudent;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        validSubject = new Subject("Math");
        invalidSubject = new Subject();
        validStudent = new Student(
                11,
                "name",
                "lastName",
                "email@gmail.com");
        invalidStudent = new Student();
    }

    @Disabled
    @Test
    void handleInvalidEmailException() throws Exception {
        mockMvcStudent.perform(post("/student/")
                .content(objectMapper.writeValueAsString(validStudent))
                .contentType("application/json"))
                .andExpect(status().isOk());
        verify(studentService).saveNewStudent(validStudent);

        mockMvcStudent.perform(post("/student/")
                .content(objectMapper.writeValueAsString(validStudent))
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
        verify(studentService).saveNewStudent(validStudent);
        verify(advice).handleInvalidEmailException(new InvalidEmailException());
    }

    @Test
    void handleConstraintViolationException() {
    }

    @Test
    void handleInvalidIdException() {
    }
}