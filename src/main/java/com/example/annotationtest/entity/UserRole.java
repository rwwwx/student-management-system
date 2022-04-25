package com.example.annotationtest.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.annotationtest.entity.Permission.*;

public enum UserRole {

    USER(Set.of(STUDENT_READ, STUDENT_WRITE)),
    ADMIN(Set.of(STUDENT_READ, STUDENT_WRITE, USER_WRITE, USER_READ));

    private final Set<Permission> permissions;

    UserRole(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getSetOfGrantedAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }

    public List<SimpleGrantedAuthority> getListOfGrantedAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "ROLE_" + this.name();
    }

    private Set<Permission> getPermissions() {
        return permissions;
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