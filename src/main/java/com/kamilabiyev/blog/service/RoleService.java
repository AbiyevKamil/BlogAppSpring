package com.kamilabiyev.blog.service;

import com.kamilabiyev.blog.model.entity.Role;
import com.kamilabiyev.blog.model.request.AddRoleRequest;

public interface RoleService {
    public Role getByName(String name);
    public void add(AddRoleRequest request);
}
