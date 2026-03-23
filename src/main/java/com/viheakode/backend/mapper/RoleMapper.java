package com.viheakode.backend.mapper;

import com.viheakode.backend.dto.response.RoleResponseDto;
import com.viheakode.backend.model.Permission;
import com.viheakode.backend.model.Role;

import java.util.List;

public class RoleMapper {
    public static RoleResponseDto toDto(Role role){
        if (role == null) return null;
        RoleResponseDto dto = new RoleResponseDto();
        dto.setRoleId(role.getRoleId());
        dto.setUuid(role.getUuid());
        dto.setRoleName(role.getRoleName());
        dto.setDescription(role.getDescription());

        List<String> permissions = role.getPermissions()
                .stream()
                .map(Permission::getPermissionName)
                .toList();

        dto.setPermissions(permissions);
        dto.setStatus(role.getStatus());
        dto.setPublisher(role.getPublisher());
        dto.setPublishedDate(role.getPublishedDate());
        dto.setModifiedDate(role.getModifiedDate());
        return dto;
    }
}
