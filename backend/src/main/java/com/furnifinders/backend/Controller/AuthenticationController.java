package com.furnifinders.backend.Controller;



import com.furnifinders.backend.Entity.Product;
import com.furnifinders.backend.Entity.User;
import com.furnifinders.backend.dto.JwtAuthenticationResponse;
import com.furnifinders.backend.dto.RefreshTokenRequest;
import com.furnifinders.backend.dto.SignInRequest;
import com.furnifinders.backend.dto.SignUpRequest;
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
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest signuprequest) {
        return ResponseEntity.ok(authenticationService.signUp(signuprequest));
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest signinRequest) {
        JwtAuthenticationResponse jwtAuthenticationResponse = authenticationService.signIn(signinRequest);
        return ResponseEntity.ok(jwtAuthenticationResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Product>> searchProducts(@PathVariable String keyword) {
        List<Product> products = userService.searchProducts(keyword);
        return ResponseEntity.ok(products);
    }
}