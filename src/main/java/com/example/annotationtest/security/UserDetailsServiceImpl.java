package com.example.annotationtest.security;

import com.example.annotationtest.entity.User;
import com.example.annotationtest.entityRepository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;
    private final UserSessionBean userSessionBean;

    @Autowired
    public UserDetailsServiceImpl(UserRepo userRepo,
                                  UserSessionBean userSessionBean) {
        this.userRepo = userRepo;
        this.userSessionBean = userSessionBean;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (userRepo.existsByEmail(email)) {
            User user = userRepo.getUserByEmail(email);
            userSessionBean.setId(user.getId());
            userSessionBean.setUserRole(user.getRole());
            return new UserDetailsImpl(
                    user.getEmail(),
                    user.getPassword(),
                    user.getRole());
        }
        throw new UsernameNotFoundException("user with this email " + email + " not found");
    }

}
