package com.example.annotationtest.entity;

import com.example.annotationtest.utils.EmailExistenceCheck;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "Student")
@Table(name = "students")
public class Student {

    public Student(int age, String firstName, String lastName, String email) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Student(int age, String firstName, String lastName, String email, Set<Subject> subjectSet) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.subjectSet = subjectSet;
    }

    public Student(long id, Student student) {
        this.id = id;
        this.age = student.getAge();
        this.firstName = student.getFirstName();
        this.email = student.getEmail();
        this.lastName = student.getLastName();
    }

    @Id
    @SequenceGenerator(name = "sequenceForStudent", sequenceName = "sequenceForStudent", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceForStudent")
    private long id;

    private int age;

    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    @EmailExistenceCheck
    @NotNull
    @Email(message = "Email should be valid")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Set<Subject> subjectSet;

}
