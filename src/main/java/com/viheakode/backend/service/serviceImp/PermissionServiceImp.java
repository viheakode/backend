package com.viheakode.backend.service.serviceImp;

import com.viheakode.backend.dto.request.PermissionRequestDto;
import com.viheakode.backend.dto.response.PermissionResponseDto;
import com.viheakode.backend.exception.ResourceDuplicateException;
import com.viheakode.backend.exception.ResourceNotFoundException;
import com.viheakode.backend.mapper.PermissionMapper;
import com.viheakode.backend.model.Permission;
import com.viheakode.backend.repository.PermissionRepository;
import com.viheakode.backend.service.IPermissionService;
import com.viheakode.backend.util.SecurityUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PermissionServiceImp implements IPermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionServiceImp(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @PreAuthorize("hasAuthority('CREATE_PERMISSION')")
    @Override
    public PermissionResponseDto createPermission(PermissionRequestDto requestDto) {

        String permissionName = requestDto.getPermissionName().toUpperCase();
        if (permissionRepository.existsByPermissionName(permissionName)) throw new ResourceDuplicateException("Permission already exists");
        Permission permission = new Permission();
        permission.setUuid(UUID.randomUUID().toString());
        permission.setPermissionName(permissionName);
        permission.setDescription(requestDto.getDescription());
        permission.setPublisher(SecurityUtil.getPublisher());
        permissionRepository.save(permission);
        return PermissionMapper.toDto(permission);
    }
    @PreAuthorize("hasAuthority('READ_PERMISSION')")
    @Override
    public List<PermissionResponseDto> getAllPermissions() {
        List<Permission> permissionList = permissionRepository.findAll();
        return permissionList.stream().map(PermissionMapper::toDto).toList();
    }

    @PreAuthorize("hasAuthority('READ_PERMISSION')")
    @Override
    public PermissionResponseDto getPermissionById(Long permissionId) {
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));
        return PermissionMapper.toDto(permission);
    }

    @PreAuthorize("hasAuthority('UPDATE_PERMISSION')")
    @Override
    public PermissionResponseDto updatePermission(Long permissionId, PermissionRequestDto requestDto) {
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));

        String permissionName = requestDto.getPermissionName().toUpperCase();
        if (permissionRepository.existsByPermissionName(permissionName)) throw new ResourceDuplicateException("Permission already exists");
        permission.setPermissionName(permissionName);
        permission.setDescription(requestDto.getDescription());
        permission.setModifiedDate(LocalDateTime.now());
        permissionRepository.save(permission);
        return PermissionMapper.toDto(permission);

    }
    @PreAuthorize("hasAuthority('DELETE_PERMISSION')")
    @Override
    public PermissionResponseDto deletePermission(Long permissionId) {
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));
        permissionRepository.delete(permission);
        return PermissionMapper.toDto(permission);
    }
}
