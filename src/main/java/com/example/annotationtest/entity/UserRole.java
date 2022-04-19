package com.example.annotationtest.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.annotationtest.entity.Permission.*;

public enum UserRole {

    USER(new HashSet<>(Set.of(STUDENT_READ, STUDENT_WRITE))),
    ADMIN(new HashSet<>(Set.of(STUDENT_READ, STUDENT_WRITE, USER_WRITE, USER_READ)));

    private final Set<Permission> permissions;

    UserRole(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getGrantedAuthorities() {
        //permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
    }


}

enum Permission {

    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    USER_WRITE("user:write"),
    USER_READ("user:read");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}