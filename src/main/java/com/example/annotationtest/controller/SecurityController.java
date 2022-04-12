package com.example.annotationtest.controller;

import com.example.annotationtest.entity.User;
import com.example.annotationtest.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SecurityController {

    private final UserService service;

    public SecurityController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("<h1> Welcome to homepage ! </h1>");
    }

    @GetMapping("/admin/getAllStudents")
    public ResponseEntity<String> getAllStudents() {
        return ResponseEntity.ok("<h1> Welcome to homepage ! </h1>");
    }

    @PostMapping("/signIn")
    public ResponseEntity<User> saveNewUser(@RequestBody User newUser) {
        return ResponseEntity.ok(service.saveUser(newUser));
    }

}
