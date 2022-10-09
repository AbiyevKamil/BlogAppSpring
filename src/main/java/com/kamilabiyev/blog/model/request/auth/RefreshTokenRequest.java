package com.kamilabiyev.blog.model.request.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RefreshTokenRequest {
    @NotBlank(message = "Refresh token can not be empty.")
    private String refreshToken;
}
