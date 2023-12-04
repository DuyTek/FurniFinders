package com.furnifinders.backend.service;


import com.furnifinders.backend.Entity.User;
import com.furnifinders.backend.dto.JwtAuthenticationResponse;
import com.furnifinders.backend.dto.RefreshTokenRequest;
import com.furnifinders.backend.dto.SignInRequest;
import com.furnifinders.backend.dto.SignUpRequest;

public interface AuthenticationService {


    User signUp(SignUpRequest signupRequest);

    JwtAuthenticationResponse signIn(SignInRequest signinRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}