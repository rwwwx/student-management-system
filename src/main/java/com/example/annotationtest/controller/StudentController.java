package com.example.annotationtest.controller;

import com.example.annotationtest.entity.Student;
import com.example.annotationtest.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.List;

@RestController
@RequestMapping("/student")
@Validated
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("home", HttpStatus.OK);
    }

    @GetMapping("/ls")
    public ResponseEntity<List<Student>> viewAllUsers() {
        return new ResponseEntity<>(studentService.viewUsers(), HttpStatus.OK);
    }

    @PostMapping("/saveNewStudent")
    public ResponseEntity<Student> saveNewStudent(@RequestBody @Valid Student student) throws RuntimeException {
        return new ResponseEntity<>(studentService.saveNewStudent(student), HttpStatus.OK);
    }

    //TODO validating
    @PostMapping("/saveNewStudents")
    public ResponseEntity<List<Student>> saveNewStudents(@RequestBody @NotNull List<@Valid Student> students) {
        return new ResponseEntity<>(studentService.saveNewUsers(students), HttpStatus.OK);
    }

//    @PostMapping("/test")
//    public ResponseEntity<Student> test() {
//        HashSet<Subject> subjects = new HashSet<>(Set.of(new Subject("math"), new Subject("math2")));
//        return new ResponseEntity<>(studentService.saveNewStudent(new Student
//                (22, "m", "m", "email", subjects)), HttpStatus.OK);
//    }

    @PostMapping("/updateStudentEmail")
    public ResponseEntity<Student> updateStudentEmail(@RequestParam long id, String newEmail) {
        return new ResponseEntity<>(studentService.updateStudentEmail(id, newEmail), HttpStatus.OK);
    }

}
