package com.example.annotationtest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Student")
@Table(name = "students")
public class Student {

    @Id
    @SequenceGenerator(name = "sequenceForStudent", sequenceName = "sequenceForStudent", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceForStudent")
    private long id;

    private int age;

    private String firstName;
    private String lastName;

    @NotNull
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Set<Subject> subjectSet;

    public Student(int age, String firstName, String lastName, String email, User owner) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.owner = owner;
    }

    public Student(int age, String firstName, String lastName, String email, User owner, Set<Subject> subjectSet) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.owner = owner;
        this.subjectSet = subjectSet;
    }

}
