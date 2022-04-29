package com.example.annotationtest.entity;

import com.example.annotationtest.utils.EmailExistsForUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany
    private List<Student> students;

    @OneToMany
    private List<Subject> subjects;

    @Column(name = "roleName")
    private String roleName;

    @Column(name = "roleId")
    private UserRole role;

    public User(String email, String password, UserRole role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.roleName = role.name();
    }

    //TODO delete this later
    public User(String email, String password, UserRole role, Student student) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.roleName = role.name();
        this.students = new ArrayList<>();
        students.add(student);
    }

    //TODO delete this later
    public User(String email, String password, Student student) {
        this.email = email;
        this.password = password;
        this.role = UserRole.USER;
        this.roleName = UserRole.USER.name();
        this.students = new ArrayList<>();
        students.add(student);
    }

}
