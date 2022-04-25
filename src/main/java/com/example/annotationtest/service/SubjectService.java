package com.example.annotationtest.service;

import com.example.annotationtest.entity.Subject;
import com.example.annotationtest.entityRepository.SubjectRepo;
import com.example.annotationtest.exception.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
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

    @Transactional
    public Subject updateSubject(long id, @Valid Subject updatedSubject) {
        return subjectRepo.save(new Subject(id, updatedSubject));
    }

    public Subject getSubjectById(long id) {
        return subjectRepo.getById(id);
    }

    @Transactional
    public void deleteSubject(long id) throws RuntimeException {
        if (subjectRepo.existsById(id)) {
            subjectRepo.deleteById(id);
        } else {
            throw new InvalidIdException();
        }
    }

}
