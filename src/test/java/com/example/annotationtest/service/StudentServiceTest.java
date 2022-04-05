package com.example.annotationtest.service;

import com.example.annotationtest.entity.Student;
import com.example.annotationtest.entityRepository.StudentRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class StudentServiceTest {

    @MockBean
    private StudentRepo studentRepo;

    @Autowired
    private StudentService studentService;

    private Student validStudent1;

    @BeforeEach
    void setUp() {
        validStudent1 = new Student(
                11,
                "name",
                "lastName",
                "email");
    }

    @Test
    void shouldSaveNewStudents() {
        when(studentRepo.save(validStudent1)).thenReturn(validStudent1);
        assertThat(studentService.saveNewStudent(validStudent1)).isEqualTo(studentRepo.save(validStudent1));
        verify(studentRepo, times(2)).save(validStudent1);
    }

}