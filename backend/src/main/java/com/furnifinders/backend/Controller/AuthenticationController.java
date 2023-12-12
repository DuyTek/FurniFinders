package com.furnifinders.backend.Controller;


import com.furnifinders.backend.Entity.Product;
import com.furnifinders.backend.dto.Request.RefreshTokenRequest;
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

@CrossOrigin(origins = "http://localhost:3030", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest signuprequest) {
        return ResponseEntity.ok(authenticationService.signUp(signuprequest));
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

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Product>> searchProducts(@PathVariable String keyword) {
        List<Product> products = userService.searchProducts(keyword);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/findAllApprovedProducts")
    public ResponseEntity<List<Product>> findAllApprovedProducts() {
        List<Product> products = userService.findAllApprovedProducts();
        return ResponseEntity.ok(products);
    }

}