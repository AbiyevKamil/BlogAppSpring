package com.kamilabiyev.blog.utils;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class SecurityUtil {
    public static Optional<UserDetails> getPrincipal() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable((UserDetails) securityContext.getAuthentication().getPrincipal());
    }
}
