package com.example.annotationtest.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "User")
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @NotNull
    private String username;
    @NotNull
    private String password;

    @JoinColumn(name = "student_id")
    @OneToMany
    private List<Student> students;

    @JoinColumn(name = "subject_id")
    @OneToMany
    private List<Subject> subjects;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Role> roles;

    //TODO constructor
    public User(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        roles.add(new Role(UserRole.USER_ROLE));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    //TODO later
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
