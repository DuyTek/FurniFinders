package com.furnifinders.backend.service.impl;


import com.furnifinders.backend.Entity.Enum.Role;
import com.furnifinders.backend.Entity.User;
import com.furnifinders.backend.Repository.UserRepository;
import com.furnifinders.backend.dto.JwtAuthenticationResponse;
import com.furnifinders.backend.dto.RefreshTokenRequest;
import com.furnifinders.backend.dto.SignInRequest;
import com.furnifinders.backend.dto.SignUpRequest;
import com.furnifinders.backend.service.AuthenticationService;
import com.furnifinders.backend.service.EntityService.UserEntityService;
import com.furnifinders.backend.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserEntityService userEntityService;



    public User signUp(SignUpRequest signupRequest) {
        User user = new User();
        user.setUser_email(signupRequest.getUser_email());
        user.setUser_first_name(signupRequest.getUser_first_name());
        user.setUser_last_name(signupRequest.getUser_last_name());
        user.setUser_phone(signupRequest.getUser_phone());
        user.setUser_role(Role.USER);
        user.setUser_password(passwordEncoder.encode(signupRequest.getUser_password()));
        return userRepository.save(user);
    }

    public JwtAuthenticationResponse signIn(SignInRequest signinRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUser_email(),
                signinRequest.getUser_password()));

        var user = userEntityService.findUserByEmail(signinRequest.getUser_email()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;

    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userEntityService.findUserByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }
}