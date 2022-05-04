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

    @Bean
    CommandLineRunner run(UserService userService, StudentService studentService) {
        return args -> {
            Student student1 = new Student(22, "name", "name2", "1@mail.com");
            Student student2 = new Student(22, "name", "name2", "2@mail.com");
            studentService.saveNewStudent(student1);
            studentService.saveNewStudent(student2);
            userService.saveUser(new User(
                    "1@mail.com",
                    "1",
                    UserRole.ADMIN,
                    student1));
            userService.saveUser(new User(
                    "2@mail.com",
                    "1",
                    UserRole.USER,
                    student2));
        };
    }
}
