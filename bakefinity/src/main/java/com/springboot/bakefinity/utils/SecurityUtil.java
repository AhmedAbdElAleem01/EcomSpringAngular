package com.springboot.bakefinity.utils;

import com.springboot.bakefinity.security.authenticationToken.JwtAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthentication) {
            return ((JwtAuthentication) authentication).getUserId();
        }
        return null;
    }

    public static String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthentication) {
            return ((JwtAuthentication) authentication).getEmail();
        }
        return null;
    }
}