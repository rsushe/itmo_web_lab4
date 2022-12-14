package com.rsushe.weblab4.service;

import com.rsushe.weblab4.dto.AuthResponse;

public interface AuthService {
    void registerNewUser(String username, String password);

    AuthResponse loginUser(String username, String password);
}
