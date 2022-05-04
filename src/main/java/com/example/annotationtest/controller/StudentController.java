package com.example.annotationtest.controller;

import com.example.annotationtest.entity.Student;
import com.example.annotationtest.entity.StudentDTO;
import com.example.annotationtest.exception.InvalidEmailException;
import com.example.annotationtest.exception.InvalidIdException;
import com.example.annotationtest.service.StudentService;
import com.example.annotationtest.service.UserService;
import com.example.annotationtest.utils.UtilityClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/user/student")
@Validated
public class StudentController {

    private final StudentService studentService;
    private final UserService userService;

    @Autowired
    public StudentController(StudentService studentService, UserService userService) {
        this.studentService = studentService;
        this.userService = userService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('student:read')")
    public ResponseEntity<List<Student>> viewAllStudentsForCurrentUser() {
        return new ResponseEntity<>(
                userService.getUserByEmail(UtilityClass.getEmailOfCurrentUser()).getStudents(),
                HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('student:write')")
    public ResponseEntity<Student> saveNewStudent(@RequestBody @Valid StudentDTO studentDTO) throws InvalidEmailException {
        return ResponseEntity.ok(studentService.saveNewStudent(studentDTO));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('student:read')")
    public ResponseEntity<Student> getStudentById(@PathVariable long id) throws InvalidIdException {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody StudentDTO studentDTOForUpdate) {
        return ResponseEntity.ok(studentService.updateStudent(id, studentDTOForUpdate));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
