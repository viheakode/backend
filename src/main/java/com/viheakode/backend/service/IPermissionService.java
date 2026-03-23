package com.viheakode.backend.service;

import com.viheakode.backend.dto.request.PermissionRequestDto;
import com.viheakode.backend.dto.response.PermissionResponseDto;

import java.util.List;

public interface IPermissionService {
    PermissionResponseDto createPermission(PermissionRequestDto requestDto);
    List<PermissionResponseDto> getAllPermissions();
    PermissionResponseDto getPermissionById(Long permissionId);
    PermissionResponseDto updatePermission(Long permissionId, PermissionRequestDto requestDto);
    PermissionResponseDto deletePermission(Long permissionId);
}
