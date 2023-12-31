package com.furnifinders.backend.Controller;


import com.furnifinders.backend.Entity.Product;
import com.furnifinders.backend.dto.Request.RefreshTokenRequest;
import com.furnifinders.backend.dto.Request.SearchProductsRequest;
import com.furnifinders.backend.dto.Request.SignInRequest;
import com.furnifinders.backend.dto.Request.SignUpRequest;
import com.furnifinders.backend.dto.Response.RefreshResponse;
import com.furnifinders.backend.dto.Response.SignInResponse;
import com.furnifinders.backend.dto.Response.SignUpResponse;
import com.furnifinders.backend.service.AuthenticationService;
import com.furnifinders.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest signuprequest) {
        SignUpResponse signUpResponse = new SignUpResponse();
        try {
            authenticationService.signUp(signuprequest);
            signUpResponse.setMessage("User registered successfully");
            return ResponseEntity.ok().body(signUpResponse);
        } catch (Exception e) {
            signUpResponse.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(signUpResponse);
        }
    }

    @PostMapping("/signIn")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest signinRequest) {
        SignInResponse signInResponse = authenticationService.signIn(signinRequest);
        return ResponseEntity.ok(signInResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        RefreshResponse refreshResponse = authenticationService.refreshToken(refreshTokenRequest);
        return ResponseEntity.ok(refreshResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestBody SearchProductsRequest searchProductsRequest) {
        List<Product> products = userService.searchProducts(searchProductsRequest.getKeyword());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/findAllApprovedProducts")
    public ResponseEntity<List<Product>> findAllApprovedProducts() {
        List<Product> products = userService.findAllApprovedProducts();
        return ResponseEntity.ok(products);
    }

}