package com.rsushe.weblab4.service;

import com.rsushe.weblab4.audited.Audited;
import com.rsushe.weblab4.dto.AuthResponse;
import com.rsushe.weblab4.entity.User;
import com.rsushe.weblab4.exception.UserAlreadyExistException;
import com.rsushe.weblab4.repository.UserRepository;
import com.rsushe.weblab4.security.JwtTokenUtil;
import com.rsushe.weblab4.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager,
                           PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public void registerNewUser(String username, String password) {
        try {
            userRepository.save(new User(username, passwordEncoder.encode(password)));
        } catch (DataIntegrityViolationException exception) {
            throw new UserAlreadyExistException("User with that username already exist");
        }

    }

    @Override
    @Audited
    public AuthResponse loginUser(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String token = jwtTokenUtil.generateToken(userDetails);
        return new AuthResponse(token);
    }
}
