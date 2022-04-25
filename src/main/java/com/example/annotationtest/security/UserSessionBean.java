package com.example.annotationtest.security;

import com.example.annotationtest.entity.UserRole;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
@Component
@SessionScope
public class UserSessionBean {

    private long id;
    private UserRole userRole;

}
