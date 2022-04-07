package com.example.annotationtest.service;

import com.example.annotationtest.entity.Student;
import com.example.annotationtest.entityRepository.StudentRepo;
import com.example.annotationtest.exception.InvalidIdException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import javax.validation.Valid;

import java.util.List;

@Slf4j
@org.springframework.stereotype.Service
public class StudentService {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public Student saveNewStudent(Student student) {
        return studentRepo.save(student);
    }

    public Student getStudentById(long id) {
        return studentRepo.getById(id);
    }

    public List<Student> viewUsers() {
        return studentRepo.findAll();
    }

    @Transactional
    public Student updateStudent(long id, @Valid Student student) {
        return studentRepo.save(new Student(id, student));
    }

    @Transactional
    public void deleteStudent(long id) throws RuntimeException {
        if (studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
        } else {
            throw new InvalidIdException();
        }
    }

}
