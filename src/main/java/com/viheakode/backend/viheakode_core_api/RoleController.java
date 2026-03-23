package com.viheakode.backend.viheakode_core_api;

import com.viheakode.backend.dto.request.RoleModifyRequestDto;
import com.viheakode.backend.dto.request.RoleRequestDto;
import com.viheakode.backend.dto.response.RoleResponseDto;
import com.viheakode.backend.service.serviceImp.RoleServiceImp;
import com.viheakode.backend.util.ApiResponseStructure;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleServiceImp roleServiceImp;

    public RoleController(RoleServiceImp roleServiceImp) {
        this.roleServiceImp = roleServiceImp;
    }

    @PostMapping
    public ResponseEntity<Object> createRole(@Valid @RequestBody RoleRequestDto requestDto){
        RoleResponseDto roleResponseDto = roleServiceImp.createRole(requestDto);
        return ApiResponseStructure.responseSuccess("Created", roleResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAllRoles(){
        List<RoleResponseDto> roleResponseDtoList = roleServiceImp.getAllRoles();
        return ApiResponseStructure.responseSuccess("Ok", roleResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<Object> getRoleById(@PathVariable Long roleId){
        RoleResponseDto roleResponseDto = roleServiceImp.getRoleById(roleId);
        return ApiResponseStructure.responseSuccess("Ok", roleResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<Object> updateRole(@PathVariable Long roleId, @Valid @RequestBody RoleRequestDto requestDto){
        RoleResponseDto roleResponseDto = roleServiceImp.updateRole(roleId, requestDto);
        return ApiResponseStructure.responseSuccess("Updated", roleResponseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Object> deleteRole(@PathVariable Long roleId){
        RoleResponseDto roleResponseDto = roleServiceImp.deleteRole(roleId);
        return ApiResponseStructure.responseSuccess("Deleted", roleResponseDto, HttpStatus.OK);
    }

    @PostMapping("/{roleId}/permissions")
    public ResponseEntity<Object> addPermissionsToRole(@PathVariable Long roleId, @Valid @RequestBody RoleModifyRequestDto requestDto){
        RoleResponseDto roleResponseDto = roleServiceImp.assignPermissionsToRole(roleId, requestDto);
        return ApiResponseStructure.responseSuccess("Created", roleResponseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{roleId}/permissions")
    public ResponseEntity<Object> removePermissionsFromRole(@PathVariable Long roleId, @Valid @RequestBody RoleModifyRequestDto requestDto){
        RoleResponseDto roleResponseDto = roleServiceImp.removePermissionsFromRole(roleId, requestDto);
        return ApiResponseStructure.responseSuccess("Deleted", roleResponseDto, HttpStatus.OK);
    }
}
