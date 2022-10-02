package com.kamilabiyev.blog.service;

import com.kamilabiyev.blog.model.request.LoginRequest;
import com.kamilabiyev.blog.model.request.RefreshTokenRequest;
import com.kamilabiyev.blog.model.request.RegisterRequest;
import com.kamilabiyev.blog.model.response.AuthResponse;

public interface UserService {
    public AuthResponse login(LoginRequest request);
    public AuthResponse register(RegisterRequest request);
    public AuthResponse refreshToken(RefreshTokenRequest request);
}
