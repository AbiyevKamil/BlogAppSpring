package com.kamilabiyev.blog.service;

import com.kamilabiyev.blog.model.request.auth.LoginRequest;
import com.kamilabiyev.blog.model.request.auth.RefreshTokenRequest;
import com.kamilabiyev.blog.model.request.auth.RegisterRequest;
import com.kamilabiyev.blog.model.response.AuthResponse;

public interface UserService {
    public AuthResponse login(LoginRequest request);
    public AuthResponse register(RegisterRequest request);
    public AuthResponse refreshToken(RefreshTokenRequest request);
}
