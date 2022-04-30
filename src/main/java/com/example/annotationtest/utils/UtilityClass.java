package com.example.annotationtest.utils;

import com.example.annotationtest.entity.Student;
import com.example.annotationtest.entity.StudentDTO;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashSet;

public class UtilityClass {

    private UtilityClass(){}

    public static String getEmailOfCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static Student studentDTOToStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setAge(studentDTO.getAge());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        if (studentDTO.getSubjectSet() != null)
            student.setSubjectSet(new HashSet<>(studentDTO.getSubjectSet()));
        else
            student.setSubjectSet(new HashSet<>());
        return student;
    }

}
