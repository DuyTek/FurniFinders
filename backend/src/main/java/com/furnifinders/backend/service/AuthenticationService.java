package com.furnifinders.backend.service;


import com.furnifinders.backend.dto.Request.RefreshTokenRequest;
import com.furnifinders.backend.dto.Request.SignInRequest;
import com.furnifinders.backend.dto.Request.SignUpRequest;
import com.furnifinders.backend.dto.Response.RefreshResponse;
import com.furnifinders.backend.dto.Response.SignInResponse;
import com.furnifinders.backend.dto.Response.SignUpResponse;

public interface AuthenticationService {


    SignUpResponse signUp(SignUpRequest signupRequest);

    SignInResponse signIn(SignInRequest signinRequest);

    RefreshResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}