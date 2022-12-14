package com.rsushe.weblab4.security;

import lombok.Getter;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class UserDetailsImpl extends User {
    private final Long userId;

    public UserDetailsImpl(String username, String password, Long userId) {
        super(username, password, List.of());
        this.userId = userId;
    }
}
