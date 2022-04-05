package com.example.annotationtest.service;

import com.example.annotationtest.entity.Student;
import com.example.annotationtest.entityRepository.StudentRepo;
import com.example.annotationtest.exception.invalidEmailException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import java.util.List;

@Slf4j
@org.springframework.stereotype.Service
public class StudentService {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    //TODO check @Qualifier
    public Student saveNewStudent(Student student) {
        return studentRepo.save(student);
    }

    public List<Student> saveNewUsers(List<Student> students) {
        return studentRepo.saveAll(students);
    }

    public List<Student> viewUsers() {
        return studentRepo.findAll();
    }

    @Transactional
    public Student updateStudentEmail(long id, String newEmail) throws RuntimeException {
        if (studentRepo.existsById(id) && !studentRepo.existsByEmail(newEmail)) {
            return studentRepo.updateStudentEmail(id, newEmail);
        } else {
            log.info("wrong email or id");
            throw new invalidEmailException();
        }
    }

}
