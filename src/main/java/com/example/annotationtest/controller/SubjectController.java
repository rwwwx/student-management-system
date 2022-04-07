package com.example.annotationtest.controller;

import com.example.annotationtest.entity.Subject;
import com.example.annotationtest.service.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.viewSubjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable long id) {
        return ResponseEntity.ok(subjectService.getSubjectById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Subject> saveNewSubject(@Valid @RequestBody Subject subject) throws RuntimeException {
        return new ResponseEntity<>(subjectService.saveNewSubject(subject), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable long id, @RequestBody Subject updatedSubject) {
        return ResponseEntity.ok(subjectService.updateSubject(id, updatedSubject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSubject(@PathVariable long id) {
        subjectService.deleteSubject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



//    @PostMapping("/test2")
//    public ResponseEntity<Subject> test() {
//        Student student = new Student
//                (22, "m", "m", "email");
//        return new ResponseEntity<>(subjectService.saveNewSubject(new Subject("math", student)), HttpStatus.OK);
//    }


}
