package com.viheakode.backend.mapper;

import com.viheakode.backend.dto.response.UserResponseDto;
import com.viheakode.backend.model.Role;
import com.viheakode.backend.model.User;

import java.util.List;

public class UserMapper {
    public static UserResponseDto toDto(User user){
        if (user == null) return null;

        UserResponseDto dto = new UserResponseDto();
        dto.setUserId(user.getUserId());
        dto.setUuid(user.getUuid());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());

        List<String> roles = user.getRoles()
                .stream()
                .map(Role::getRoleName)
                .toList();
        dto.setRoles(roles);

        dto.setStatus(user.getStatus());
        dto.setPublisher(user.getPublisher());
        dto.setPublishedDate(user.getPublishedDate());
        dto.setModifiedDate(user.getModifiedDate());
        return dto;
    }
}
