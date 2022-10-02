package com.kamilabiyev.blog.controller;


import com.kamilabiyev.blog.model.request.LoginRequest;
import com.kamilabiyev.blog.model.request.RefreshTokenRequest;
import com.kamilabiyev.blog.model.request.RegisterRequest;
import com.kamilabiyev.blog.model.response.AuthResponse;
import com.kamilabiyev.blog.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    @SecurityRequirements
    public ResponseEntity<AuthResponse> login(
           @Valid @RequestBody(required = false) LoginRequest loginRequest) {
        AuthResponse authResponse = userService.login(loginRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    @SecurityRequirements
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody(required = false) RegisterRequest registerRequest) {
        AuthResponse authResponse = userService.register(registerRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/refreshToken")
    @SecurityRequirements
    public ResponseEntity<AuthResponse> refreshToken(
            @Valid @RequestBody(required = false) RefreshTokenRequest refreshTokenRequest) {
        AuthResponse authResponse = userService.refreshToken(refreshTokenRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}