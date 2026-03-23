package com.viheakode.backend.service;

import com.viheakode.backend.dto.request.RoleModifyRequestDto;
import com.viheakode.backend.dto.request.RoleRequestDto;
import com.viheakode.backend.dto.response.RoleResponseDto;

import java.util.List;

public interface IRoleService {
    RoleResponseDto createRole(RoleRequestDto requestDto);
    List<RoleResponseDto> getAllRoles();
    RoleResponseDto getRoleById(Long roleId);
    RoleResponseDto updateRole(Long roleId, RoleRequestDto requestDto);
    RoleResponseDto deleteRole(Long roleId);
    RoleResponseDto assignPermissionsToRole(Long roleId, RoleModifyRequestDto requestDto);
    RoleResponseDto removePermissionsFromRole(Long roleId, RoleModifyRequestDto requestDto);

}
