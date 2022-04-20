package com.example.annotationtest.controller;

import com.example.annotationtest.entity.Student;
import com.example.annotationtest.entity.User;
import com.example.annotationtest.entity.UserRole;
import com.example.annotationtest.service.StudentService;
import com.example.annotationtest.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
public class SecurityController {

    private final UserService userService;
    private final StudentService studentService;

    public SecurityController(UserService userService, StudentService studentService) {
        this.userService = userService;
        this.studentService = studentService;
    }

    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("<h1> Welcome to homepage ! </h1>");
    }

    @GetMapping("/admin/getAllStudents")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/admin/getAllUsers")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/signIn")
    public ResponseEntity<User> saveNewUser(@Valid @RequestBody User newUser) {
        return ResponseEntity.ok(userService.saveUser(newUser));
    }

}
