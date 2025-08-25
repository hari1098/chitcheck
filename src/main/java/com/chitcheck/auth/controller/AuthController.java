package com.chitcheck.auth.controller;

import com.chitcheck.auth.dto.AuthRequest;
import com.chitcheck.auth.dto.AuthResponse;
import com.chitcheck.auth.dto.ChangePasswordRequest;
import com.chitcheck.auth.dto.RegisterRequest;
import com.chitcheck.auth.dto.VerifyRequest;
import com.chitcheck.auth.model.User;
import com.chitcheck.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/verify")
    public ResponseEntity<AuthResponse> verify(@RequestBody VerifyRequest request) {
        return ResponseEntity.ok(authService.verifyAccount(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping("/home")
    public ResponseEntity<String> homePage() {
        return ResponseEntity.ok("Welcome to the ChitCheck Home Page!");
    }

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            @AuthenticationPrincipal User user
    ) {
        authService.changePassword(request, user);
        return ResponseEntity.ok().build();
    }
}