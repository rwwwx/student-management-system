package com.example.annotationtest.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "Role")
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private UserRole userRole;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String getAuthority() {
        return userRole.name();
    }

}