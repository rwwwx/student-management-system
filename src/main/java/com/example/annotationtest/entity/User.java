package com.example.annotationtest.entity;

import com.example.annotationtest.utils.EmailExistsForUser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import java.util.List;

@Getter
@Setter
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

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Student> students;

    @OneToMany
    private List<Subject> subjects;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole role = UserRole.USER;

    public User(String email, String password, UserRole role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

}
