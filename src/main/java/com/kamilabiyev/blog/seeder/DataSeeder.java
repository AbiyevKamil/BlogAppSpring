package com.kamilabiyev.blog.seeder;

import com.kamilabiyev.blog.model.entity.Role;
import com.kamilabiyev.blog.properties.constants.RoleDefaults;
import com.kamilabiyev.blog.repository.RoleRepository;
import com.kamilabiyev.blog.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DataSeeder {
    RoleRepository roleRepository;
    UserRepository userRepository;

    public void seed() {
        if ((roleRepository.findByName(RoleDefaults.BASIC_USER.toString())).isEmpty()) {
            var role = new Role();
            role.setName(RoleDefaults.BASIC_USER.toString());
            roleRepository.save(role);
        }
    }
}
