package com.rsushe.weblab4.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.List;

public class AuthenticationToken extends AbstractAuthenticationToken {

    private final Long userId;

    public AuthenticationToken(Long userId) {
        super(List.of());
        this.userId = userId;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }
}
