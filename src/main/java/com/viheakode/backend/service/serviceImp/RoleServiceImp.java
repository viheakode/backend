package com.viheakode.backend.service.serviceImp;

import com.viheakode.backend.dto.request.RoleModifyRequestDto;
import com.viheakode.backend.dto.request.RoleRequestDto;
import com.viheakode.backend.dto.response.RoleResponseDto;
import com.viheakode.backend.exception.ResourceDuplicateException;
import com.viheakode.backend.exception.ResourceNotFoundException;
import com.viheakode.backend.mapper.RoleMapper;
import com.viheakode.backend.model.Permission;
import com.viheakode.backend.model.Role;
import com.viheakode.backend.repository.PermissionRepository;
import com.viheakode.backend.repository.RoleRepository;
import com.viheakode.backend.service.IRoleService;
import com.viheakode.backend.util.SecurityUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class RoleServiceImp implements IRoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleServiceImp(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @PreAuthorize("hasAuthority('CREATE_ROLE')")
    @Override
    public RoleResponseDto createRole(RoleRequestDto requestDto) {

        String roleName = requestDto.getRoleName().toUpperCase();
        if (roleRepository.existsByRoleName(roleName)) throw new ResourceDuplicateException("Role already exists");
        Role role = new Role();
        role.setUuid(UUID.randomUUID().toString());
        role.setRoleName(roleName);
        role.setDescription(requestDto.getDescription());
        role.setPublisher(SecurityUtil.getPublisher());
        roleRepository.save(role);
        return RoleMapper.toDto(role);
    }

    @PreAuthorize("hasAuthority('READ_ROLE')")
    @Override
    public List<RoleResponseDto> getAllRoles() {
        List<Role> roleList = roleRepository.findAll();
        return roleList.stream().map(RoleMapper::toDto).toList();
    }

    @PreAuthorize("hasAuthority('READ_ROLE')")
    @Override
    public RoleResponseDto getRoleById(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        return RoleMapper.toDto(role);
    }

    @PreAuthorize("hasAuthority('UPDATE_ROLE')")
    @Override
    public RoleResponseDto updateRole(Long roleId, RoleRequestDto requestDto) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        String roleName = requestDto.getRoleName().toUpperCase();
        if (roleRepository.existsByRoleName(roleName)) throw new ResourceDuplicateException("Role already exists");
        role.setRoleName(roleName);
        role.setDescription(requestDto.getDescription());
        role.setModifiedDate(LocalDateTime.now());
        roleRepository.save(role);
        return RoleMapper.toDto(role);
    }

    @PreAuthorize("hasAuthority('DELETE_ROLE')")
    @Override
    public RoleResponseDto deleteRole(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        roleRepository.delete(role);
        return RoleMapper.toDto(role);
    }

    @PreAuthorize("hasAuthority('MODIFY_ROLE')")
    @Override
    public RoleResponseDto assignPermissionsToRole(Long roleId, RoleModifyRequestDto requestDto) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        Set<Permission> permissions = new HashSet<>(role.getPermissions());
        for (String permissionName : requestDto.getPermissions()){
            Permission permission = permissionRepository.findByPermissionName(permissionName.toUpperCase())
                    .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));
            boolean exists = role.getPermissions().stream()
                    .anyMatch(p -> p.getPermissionId().equals(permission.getPermissionId()));
            if (exists) throw new ResourceDuplicateException("Role already assigned to role");
            permissions.add(permission);
        }
        role.setPermissions(permissions);
        role.setModifiedDate(LocalDateTime.now());
        roleRepository.save(role);
        return RoleMapper.toDto(role);
    }

    @PreAuthorize("hasAuthority('MODIFY_ROLE')")
    @Override
    public RoleResponseDto removePermissionsFromRole(Long roleId, RoleModifyRequestDto requestDto) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        Set<Permission> permissions = new HashSet<>(role.getPermissions());
        for (String permissionName : requestDto.getPermissions()){
            Permission permission = permissionRepository.findByPermissionName(permissionName.toUpperCase())
                    .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));
            boolean exists = role.getPermissions().stream()
                    .anyMatch(p -> p.getPermissionId().equals(permission.getPermissionId()));
            if (!exists) throw new ResourceNotFoundException("Role not assigned to role");
            permissions.removeIf(p -> p.getPermissionId().equals(permission.getPermissionId()));
        }
        role.setPermissions(permissions);
        role.setModifiedDate(LocalDateTime.now());
        roleRepository.save(role);
        return RoleMapper.toDto(role);
    }
}
