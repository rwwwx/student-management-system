package com.example.annotationtest.entity;


import com.example.annotationtest.utils.SubjectNameExists;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Table(name = "subjects")
@Entity(name = "Subject")
public class Subject {

    @Id
    @SequenceGenerator(name = "sequenceForSubject", sequenceName = "sequenceForSubject", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceForSubject")
    private long id;

    @Column(unique = true, nullable = false, name = "subject_name")
    @NotNull
    @SubjectNameExists
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    public Subject(String name) {
        this.name = name;
    }

    public Subject(String name, Student student) {
        this.name = name;
        this.student = student;
    }

    public Subject(long id, Subject subject) {
        this.id = id;
        this.name = subject.getName();
        this.student = subject.getStudent();
    }

}
