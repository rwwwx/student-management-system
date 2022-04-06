package com.example.annotationtest.controller;

import com.example.annotationtest.entity.Student;
import com.example.annotationtest.entity.Subject;
import com.example.annotationtest.service.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/ls")
    public List<Subject> viewAllSubjects() {
        return subjectService.viewSubjects();
    }

    @PostMapping("/saveNewSubject")
    public ResponseEntity<Subject> saveNewSubject(@Valid @RequestBody Subject subject) throws RuntimeException {
        return new ResponseEntity<>(subjectService.saveNewSubject(subject), HttpStatus.OK);
    }

    @PostMapping("/saveNewSubjects")
    public ResponseEntity<List<Subject>> saveNewSubjects(@RequestBody ArrayList<@Valid Subject> subjects) throws RuntimeException {
        return new ResponseEntity<>(subjectService.saveNewUsers(subjects), HttpStatus.OK);
    }

    @PostMapping("/test2")
    public ResponseEntity<Subject> test() {
        Student student = new Student
                (22, "m", "m", "email");
        return new ResponseEntity<>(subjectService.saveNewSubject(new Subject("math", student)), HttpStatus.OK);
    }


}
