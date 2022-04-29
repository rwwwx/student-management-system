package com.example.annotationtest.service;

import com.example.annotationtest.entity.User;
import com.example.annotationtest.entityRepository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepo userRepo,
                       @Qualifier("myEncoder") BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User saveUser(User newUser) {
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        return userRepo.save(newUser);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(long id) {
        return userRepo.getById(id);
    }

    public User getUserByEmail(String name) {
        return userRepo.getUserByEmail(name);
    }

}
