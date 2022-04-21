package com.example.annotationtest.controller;

import com.example.annotationtest.entity.Student;
import com.example.annotationtest.security.UserSessionBean;
import com.example.annotationtest.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/student")
@Validated
public class StudentController {

    private final StudentService studentService;
    private final UserSessionBean userSessionBean;

    @Autowired
    public StudentController(StudentService studentService, UserSessionBean userSessionBean) {
        this.studentService = studentService;
        this.userSessionBean = userSessionBean;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('student:read')")
    public ResponseEntity<List<Student>> viewAllUsers() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('student:write')")
    public ResponseEntity<Student> saveNewStudent(@RequestBody @Valid Student student) throws RuntimeException {
        return ResponseEntity.ok(studentService.saveNewStudent(student));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('student:read')")
    public ResponseEntity<Student> getStudentById(@PathVariable long id) throws RuntimeException {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student updatedStudent) {
        return ResponseEntity.ok(studentService.updateStudent(id, updatedStudent));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
