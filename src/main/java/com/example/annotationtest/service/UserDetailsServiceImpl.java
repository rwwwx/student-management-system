package com.example.annotationtest.service;

import com.example.annotationtest.entity.User;
import com.example.annotationtest.entityRepository.UserRepo;
import com.example.annotationtest.security.UserDetailsImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserDetailsServiceImpl(UserRepo userRepo,
                                  @Qualifier("myEncoder") BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("loadUserByUsername");
        if (userRepo.existsByEmail(email)) {
            User user = userRepo.getUserByEmail(email);
            return new UserDetailsImpl(
                    user.getEmail(),
                    bCryptPasswordEncoder.encode(user.getPassword()),
                    user.getRole());
        }
        throw new UsernameNotFoundException("user with this email " + email + " not found");
    }

}
