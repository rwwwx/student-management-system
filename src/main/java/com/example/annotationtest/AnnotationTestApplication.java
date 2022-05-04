package com.example.annotationtest;

import com.example.annotationtest.entity.Student;
import com.example.annotationtest.entity.User;
import com.example.annotationtest.entity.UserRole;
import com.example.annotationtest.service.StudentService;
import com.example.annotationtest.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.*")
public class AnnotationTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnnotationTestApplication.class, args);
    }

}
