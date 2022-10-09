package com.kamilabiyev.blog.model.request.role;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class AddRoleRequest {
    @NotBlank(message = "Name can not be empty.")
    private String name;
}
