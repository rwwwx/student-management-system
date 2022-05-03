package com.example.annotationtest.service;

import com.example.annotationtest.entity.Student;
import com.example.annotationtest.entity.StudentDTO;
import com.example.annotationtest.entityRepository.StudentRepo;
import com.example.annotationtest.entityRepository.UserRepo;
import com.example.annotationtest.exception.InvalidIdException;
import com.example.annotationtest.utils.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@org.springframework.stereotype.Service
public class StudentService {

    private final StudentRepo studentRepo;
    private final UserRepo userRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo, UserRepo userRepo) {
        this.studentRepo = studentRepo;
        this.userRepo = userRepo;
    }

    //TODO refactor
    public Student saveNewStudent(StudentDTO studentDTO) {
        Student student = UtilityClass.convertToEntity(studentDTO);
        return studentRepo.save(student);
    }

    public Student getStudentById(long id) {
        if (isStudentExists(id)) {
            var student = studentRepo.getById(id);
            if (isAdmin())
                return student;
            if (isStudentOwnerEqualToCurrentUser(student))
                return student;
        }
        throw new InvalidIdException();
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    //TODO test this method
    @Transactional
    public Student updateStudent(long id, StudentDTO studentDTO) throws InvalidIdException {
        if (isStudentExists(id)) {
            var studentForUpdate = studentRepo.getById(id);
            UtilityClass.modifyStudentFieldsFromDTO(studentForUpdate, studentDTO);
            if (isAdmin())
                return studentRepo.save(studentForUpdate);
            if (isStudentOwnerEqualToCurrentUser(studentForUpdate))
                return studentRepo.save(studentForUpdate);
        }
        throw new InvalidIdException();
    }

    @Transactional
    public void deleteStudent(long id) throws InvalidIdException {
        if (isStudentExists(id)) {
            if (isAdmin())
                studentRepo.deleteById(id);
            if (isStudentOwnerEqualToCurrentUser(studentRepo.getById(id)))
                studentRepo.deleteById(id);
        }
        throw new InvalidIdException();
    }

    private boolean isStudentExists(long id) {
        return studentRepo.existsById(id);
    }

    private String getRoleOfCurrentUser() {
        return userRepo.getByEmail(UtilityClass.getEmailOfCurrentUser()).getRole().name();
    }

    private boolean isAdmin() {
        return getRoleOfCurrentUser().equals("ADMIN");
    }

    private boolean isStudentOwnerEqualToCurrentUser(Student student) {
        return student.getOwner().getEmail().equals(UtilityClass.getEmailOfCurrentUser());
    }

}
