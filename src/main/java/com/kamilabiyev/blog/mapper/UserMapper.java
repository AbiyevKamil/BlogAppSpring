package com.kamilabiyev.blog.mapper;

import com.kamilabiyev.blog.model.dto.UserDTO;
import com.kamilabiyev.blog.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }

    public List<UserDTO> toUserDTOList(List<User> users) {
        return users.stream().map(this::toUserDTO).collect(Collectors.toList());
    }
}
