package com.kamilabiyev.blog.model.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegisterRequest {
    @NotBlank(message = "Username can not be empty.")
    private String username;
    @NotBlank(message = "Email can not be empty.")
    @Email(message = "Email is not valid.")
    private String email;
    @NotBlank(message = "Password can not be empty.")
    private String password;
}
