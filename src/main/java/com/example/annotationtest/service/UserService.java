package com.example.annotationtest.service;

import com.example.annotationtest.entity.User;
import com.example.annotationtest.entityRepository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User saveUser(User newUser) {
        return userRepo.save(newUser);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

}
