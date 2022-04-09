package com.example.annotationtest.controller;

import com.example.annotationtest.entity.User;
import com.example.annotationtest.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<User> registration(@Valid @RequestBody User newUser) {
        return ResponseEntity.ok(userService.saveUser(newUser));
    }

}
