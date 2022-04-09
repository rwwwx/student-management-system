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

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
                "email@gmail.com");
    }

    @Test
    void shouldSaveNewStudents() {
        when(studentRepo.save(validStudent1)).thenReturn(validStudent1);
        assertThat(studentService.saveNewStudent(validStudent1)).isEqualTo(studentRepo.save(validStudent1));
        verify(studentRepo, times(2)).save(validStudent1);
    }

    @Test
    void shouldGetStudentById() {
        when(studentRepo.getById(1L)).thenReturn(validStudent1);
        assertThat(studentService.getStudentById(1)).isEqualTo(studentRepo.getById(1L));
        verify(studentRepo, times(2)).getById(1L);
    }

    @Test
    void shouldViewUsers() {
        when(studentRepo.findAll()).thenReturn(List.of(validStudent1));
        assertThat(studentService.viewUsers()).isEqualTo(studentRepo.findAll());
        verify(studentRepo, times(2)).findAll();
    }

    @Test
    void shouldUpdateUser() {
        when(studentRepo.save(validStudent1)).thenReturn(validStudent1);
        when(studentService.updateStudent(1, validStudent1)).thenReturn(validStudent1);
        assertThat(studentService.updateStudent(1, validStudent1)).isEqualTo(studentRepo.save(validStudent1));
        verify(studentRepo).save(validStudent1);
    }

    @Test
    void shouldDeleteUserIfItExists() {
        when(studentRepo.existsById(1L)).thenReturn(true);
        verify(studentService, times(1)).deleteStudent(1L);
    }


}