package com.example.annotationtest.entityRepository;

import com.example.annotationtest.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepoTest {

    @Autowired
    private StudentRepo studentRepo;

    private Student validStudent;

    @BeforeEach
    void setUp() {
        validStudent = new Student(
                11,
                "name",
                "lastName",
                "email");
    }

    @Test
    void shouldGetTrueWhenEmailIsExist() {
        studentRepo.save(validStudent);
        assertThat(studentRepo.existsByEmail(validStudent.getEmail())).isTrue();
    }

    @Test
    void shouldGetFalseWhenEmailIsNotExist() {
        assertThat(studentRepo.existsByEmail(validStudent.getEmail())).isFalse();
    }

}