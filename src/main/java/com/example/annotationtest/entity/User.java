package com.example.annotationtest.entity;

import com.example.annotationtest.utils.EmailExistsForUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Persistent;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "User")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @NotNull
    @Email
    @EmailExistsForUser
    @Column(name = "user_email")
    private String email;

    @NotNull
    private String password;

    @JoinColumn(name = "student_id")
    @OneToMany
    private List<Student> students;

    @JoinColumn(name = "subject_id")
    @OneToMany
    private List<Subject> subjects;

    @Persistent
    UserRole role;

    public User(String email,String password, UserRole role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        role = UserRole.USER;
    }

}
