package com.furnifinders.backend.service.impl;


import com.furnifinders.backend.Entity.Enum.Role;
import com.furnifinders.backend.Entity.User;
import com.furnifinders.backend.Repository.UserRepository;
import com.furnifinders.backend.dto.Response.JwtAuthenticationResponse;
import com.furnifinders.backend.dto.Request.RefreshTokenRequest;
import com.furnifinders.backend.dto.Request.SignInRequest;
import com.furnifinders.backend.dto.Request.SignUpRequest;
import com.furnifinders.backend.dto.Response.RefreshResponse;
import com.furnifinders.backend.dto.Response.SignInResponse;
import com.furnifinders.backend.dto.Response.SignUpResponse;
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



    public SignUpResponse signUp(SignUpRequest signupRequest) {
        SignUpResponse signUpResponse = new SignUpResponse();
        User user = new User();
        user.setUser_email(signupRequest.getUser_email());
        user.setUser_first_name(signupRequest.getUser_first_name());
        user.setUser_last_name(signupRequest.getUser_last_name());
        user.setUser_phone(signupRequest.getUser_phone());
        user.setUser_role(Role.USER);
        user.setUser_password(passwordEncoder.encode(signupRequest.getUser_password()));

        User checkUser = userEntityService.findUserByEmail(signupRequest.getUser_email()).orElse(null);
        if (checkUser != null) {
            signUpResponse.setMessage("Given email is already registered");
            return signUpResponse;
        }

        checkUser = userEntityService.findUserByPhone(signupRequest.getUser_phone()).orElse(null);
        if (checkUser != null) {
            signUpResponse.setMessage("Given phone number is already registered");
            return signUpResponse;
        }
        signUpResponse.setMessage("User registered successfully");
        userRepository.save(user);
        return signUpResponse;
    }

    public SignInResponse signIn(SignInRequest signinRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUser_email(),
                signinRequest.getUser_password()));

        var user = userEntityService.findUserByEmail(signinRequest.getUser_email()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);


        return getSignInResponse(user, jwt, refreshToken);

    }

    private static SignInResponse getSignInResponse(User user, String jwt, String refreshToken) {
        SignInResponse signInResponse = new SignInResponse();
        signInResponse.setUser_id(user.getUser_id());
        signInResponse.setUser_first_name(user.getUser_first_name());
        signInResponse.setUser_last_name(user.getUser_last_name());
        signInResponse.setUser_email(user.getUser_email());
        signInResponse.setUser_phone(user.getUser_phone());
        signInResponse.setUser_role(user.getUser_role());
        signInResponse.setToken(jwt);
        signInResponse.setRefreshToken(refreshToken);
        signInResponse.setUser_address(user.getUser_address());
        signInResponse.setUser_dob(user.getUser_dob());
        signInResponse.setUser_gender(user.getUser_gender());
        return signInResponse;
    }

    public RefreshResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userEntityService.findUserByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return getRefreshResponse(refreshTokenRequest, user, jwt);
        }
        return null;
    }

    private static RefreshResponse getRefreshResponse(RefreshTokenRequest refreshTokenRequest, User user, String jwt) {
        RefreshResponse refreshResponse = new RefreshResponse();
        refreshResponse.setUser_id(user.getUser_id());
        refreshResponse.setUser_first_name(user.getUser_first_name());
        refreshResponse.setUser_last_name(user.getUser_last_name());
        refreshResponse.setUser_email(user.getUser_email());
        refreshResponse.setUser_phone(user.getUser_phone());
        refreshResponse.setUser_role(user.getUser_role());
        refreshResponse.setToken(jwt);
        refreshResponse.setRefreshToken(refreshTokenRequest.getToken());
        return refreshResponse;
    }
}