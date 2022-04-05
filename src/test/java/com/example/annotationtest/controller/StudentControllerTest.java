package com.example.annotationtest.controller;

import com.example.annotationtest.entityRepository.SubjectRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.annotationtest.entity.Student;
import com.example.annotationtest.entityRepository.StudentRepo;
import com.example.annotationtest.service.StudentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(controllers = StudentController.class)
class StudentControllerTest {

    @MockBean
    private StudentService studentService;

    @MockBean
    private StudentRepo studentRepo;
    @MockBean
    private SubjectRepo subjectRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private Student validStudent;
    private Student invalidStudent;

    @BeforeEach
    void setUp() {
        validStudent = new Student(
                11,
                "name",
                "lastName",
                "email");
        invalidStudent = new Student();
    }

    @Test
    void home() throws Exception {
        MvcResult result = mockMvc.perform(get("/")
                        .contentType("application/json"))
                        .andExpect(status().isOk())
                        .andReturn();
        assertThat(result.getResponse().getContentAsString()).isEqualTo("home");
    }

    @Test
    void shouldSaveValidNewStudent() throws Exception {
        mockMvc.perform(post("/saveNewStudent")
                        .content(objectMapper.writeValueAsString(validStudent))
                        .contentType("application/json"))
                        .andExpect(status().isOk());
        verify(studentService).saveNewStudent(validStudent);
    }

    @Test
    void shouldThrowsExceptionIfInputIsInvalid() throws Exception {
        mockMvc.perform(post("/saveNewStudent")
                        .content(objectMapper.writeValueAsString(invalidStudent))
                        .contentType("application/json"))
                        .andExpect(status().isBadRequest());
        verify(studentService, never()).saveNewStudent(validStudent);
    }

    @Test
    void whenInputIsValidThenCallBusinessLogic() throws Exception {
        ArgumentCaptor<Student> userCaptor = ArgumentCaptor.forClass(Student.class);
        mockMvc.perform(post("/saveNewStudent")
                        .content(objectMapper.writeValueAsString(validStudent))
                        .contentType("application/json"))
                        .andExpect(status().isOk());
        verify(studentService).saveNewStudent(userCaptor.capture());
        assertThat(userCaptor.getValue()).isEqualTo(validStudent);
    }

    @Test
    void whenInputIsValidThenReturnValidOutput() throws Exception {
        Mockito.when(studentService.saveNewStudent(validStudent)).thenReturn(validStudent);
        MvcResult result = mockMvc.perform(post("/saveNewStudent")
                .content(objectMapper.writeValueAsString(validStudent))
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();
        verify(studentService).saveNewStudent(validStudent);
        assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(studentService.saveNewStudent(validStudent)));
    }

}