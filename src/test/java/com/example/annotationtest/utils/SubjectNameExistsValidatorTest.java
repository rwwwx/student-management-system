package com.example.annotationtest.utils;

import com.example.annotationtest.controller.StudentController;
import com.example.annotationtest.entity.Student;
import com.example.annotationtest.entityRepository.StudentRepo;
import com.example.annotationtest.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class SubjectNameExistsValidatorTest {

    Student student1;
    Student student2;

    @MockBean
    private EmailExistsValidatorForStudent emailExistsValidatorForStudent;
    @MockBean
    private StudentController studentController;
    @MockBean
    private StudentService studentService;
    @MockBean
    private StudentRepo studentRepo;

    @BeforeEach
    void setUp() {
         student1 = new Student(
                1,
                "name1",
                "name1",
                "email1@email.com"
        );
    }

    @Test
    void initialize() {
    }

    @Test
    void isValid() {
        studentController.saveNewStudent(student1);
        studentController.saveNewStudent(student1);

    }

    @Test
    void validation() {
    }
}