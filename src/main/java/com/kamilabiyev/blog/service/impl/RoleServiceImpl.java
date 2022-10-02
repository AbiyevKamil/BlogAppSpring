package com.kamilabiyev.blog.service.impl;

import com.kamilabiyev.blog.exception.CustomException;
import com.kamilabiyev.blog.model.entity.Role;
import com.kamilabiyev.blog.model.request.AddRoleRequest;
import com.kamilabiyev.blog.repository.RoleRepository;
import com.kamilabiyev.blog.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> {
            throw new CustomException(String.format("Role with name %s not found.", name), HttpStatus.NOT_FOUND);
        });
    }

    @Override
    @Transactional
    public void add(AddRoleRequest request) {
        Optional<Role> role = roleRepository.findByName(request.getName());
        if (role.isEmpty()) {
            Role newRole = new Role();
            newRole.setName(request.getName());
            roleRepository.save(newRole);
        }
    }
}
