package com.kamilabiyev.blog.controller;


import com.kamilabiyev.blog.model.request.AddRoleRequest;
import com.kamilabiyev.blog.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {
    private final RoleService roleService;

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    public ResponseEntity add(
            @RequestBody AddRoleRequest request) {
        roleService.add(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}