package com.example.annotationtest.utils;

import org.springframework.security.core.context.SecurityContextHolder;

public class UtilityClass {

    public static String getEmailOfCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
