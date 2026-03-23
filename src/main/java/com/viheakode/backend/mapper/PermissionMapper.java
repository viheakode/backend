package com.viheakode.backend.mapper;

import com.viheakode.backend.dto.response.PermissionResponseDto;
import com.viheakode.backend.model.Permission;

public class PermissionMapper {
    public static PermissionResponseDto toDto(Permission permission){
        if (permission == null) return null;
        PermissionResponseDto dto = new PermissionResponseDto();
        dto.setPermissionId(permission.getPermissionId());
        dto.setUuid(permission.getUuid());
        dto.setPermissionName(permission.getPermissionName());
        dto.setDescription(permission.getDescription());
        dto.setStatus(permission.getStatus());
        dto.setPublisher(permission.getPublisher());
        dto.setPublishedDate(permission.getPublishedDate());
        dto.setModifiedDate(permission.getModifiedDate());
        return dto;
    }
}
