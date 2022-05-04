package com.example.annotationtest.entity;

import com.example.annotationtest.utils.EmailExistsForStudent;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import java.util.Set;

@Data
@NoArgsConstructor
public class StudentDTO {

    @NotNull
    private int age;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private User owner;

    @EmailExistsForStudent
    @NotNull
    @Email(message = "Email should be valid")
    private String email;

    private Set<Subject> subjectSet;

    public StudentDTO(int age, String firstName, String lastName, String email, User owner) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.owner = owner;
    }

    public StudentDTO(int age, String firstName, String lastName, String email) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


}
