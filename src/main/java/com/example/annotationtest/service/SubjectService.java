package com.example.annotationtest.service;

import com.example.annotationtest.entity.Subject;
import com.example.annotationtest.entityRepository.SubjectRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepo subjectRepo;

    @Autowired
    public SubjectService(SubjectRepo subjectRepo) {
        this.subjectRepo = subjectRepo;
    }

    public Subject saveNewSubject(Subject subject) {
        return subjectRepo.save(subject);
    }

    public List<Subject> viewSubjects() {
        return subjectRepo.findAll();
    }

    public List<Subject> saveNewUsers(List<Subject> subjects) {
        return subjectRepo.saveAll(subjects);
    }
}
