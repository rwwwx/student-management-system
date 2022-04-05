package com.example.annotationtest.controller;

import com.example.annotationtest.entity.Subject;
import com.example.annotationtest.entityRepository.StudentRepo;
import com.example.annotationtest.entityRepository.SubjectRepo;
import com.example.annotationtest.service.SubjectService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;


import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(controllers = SubjectController.class)
class SubjectControllerTest {

    @MockBean
    private SubjectService subjectService;

    @MockBean
    private SubjectRepo subjectRepo;
    @MockBean
    private StudentRepo studentRepo;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Subject validSubject;
    private Subject invalidSubject;

    @BeforeEach
    void setUp() {
        validSubject = new Subject("Math");
        invalidSubject = new Subject();
    }

    @Test
    void shouldSaveValidSubject() throws Exception {
        mockMvc.perform(post("/saveNewSubject")
                .content(objectMapper.writeValueAsString(validSubject))
                .contentType("application/json"))
                .andExpect(status().isOk());
        verify(subjectService).saveNewSubject(validSubject);
    }
}