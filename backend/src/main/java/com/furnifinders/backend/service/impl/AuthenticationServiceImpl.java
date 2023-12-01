package com.furnifinders.backend.service.impl;

import com.furnifinders.backend.Entity.Role;
import com.furnifinders.backend.Entity.User;
import com.furnifinders.backend.dto.JwtAuthenticationResponse;
import com.furnifinders.backend.dto.SignInRequest;
import com.furnifinders.backend.dto.SignUpRequest;
import com.furnifinders.backend.repository.UserRepository;
import com.furnifinders.backend.service.AuthenticationService;
import com.furnifinders.backend.service.JWTService;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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


    private final AuthenticationManager authenticationManager;


    private final JWTService jwtService;

    public User signup(SignUpRequest signUpRequest){
        User user = new User();


        user.setEmail(signUpRequest.getEmail());
        user.setFirstname(signUpRequest.getFirstname());
        user.setLastname(signUpRequest.getLastname());
        user.setRole(Role.USER);                                            //do not allow users to create Role
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));


        return userRepository.save(user);
    }


    @SneakyThrows
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest)  {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),
                signInRequest.getPassword()));

        var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new IllegalAccessException("Invalid email or password"));

        var jwt = jwtService.generateToken(user);

        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;





    }
}
