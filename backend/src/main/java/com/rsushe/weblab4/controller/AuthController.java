package com.rsushe.weblab4.controller;

import com.rsushe.weblab4.dto.AuthRequest;
import com.rsushe.weblab4.dto.ErrorMessage;
import com.rsushe.weblab4.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.hibernate.internal.util.StringHelper.isNotEmpty;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest authRequest) {
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();
        if (StringUtils.hasText(username) && isNotEmpty(password)) {
            authService.registerNewUser(username, password);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body(new ErrorMessage("Invalid input"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();
        return ResponseEntity.ok(authService.loginUser(username, password));
    }
}
