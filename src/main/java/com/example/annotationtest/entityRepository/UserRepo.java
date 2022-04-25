package com.example.annotationtest.entityRepository;

import com.example.annotationtest.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User getUserByEmail(String email);
    boolean existsByEmail(String email);

}
