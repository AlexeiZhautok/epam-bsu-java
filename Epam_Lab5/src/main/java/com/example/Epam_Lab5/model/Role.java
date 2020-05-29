package com.example.Epam_Lab5.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN,
    TEACHER,
    GUEST;

    @Override
    public String getAuthority() {
        return name();
    }
}
