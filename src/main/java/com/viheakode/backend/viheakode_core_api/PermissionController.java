package com.viheakode.backend.viheakode_core_api;

import com.viheakode.backend.dto.request.PermissionRequestDto;
import com.viheakode.backend.dto.response.PermissionResponseDto;
import com.viheakode.backend.service.serviceImp.PermissionServiceImp;
import com.viheakode.backend.util.ApiResponseStructure;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permissions")
public class PermissionController {

    private final PermissionServiceImp permissionServiceImp;

    public PermissionController(PermissionServiceImp permissionServiceImp) {
        this.permissionServiceImp = permissionServiceImp;
    }

    @PostMapping
    public ResponseEntity<Object> createPermission(@Valid @RequestBody PermissionRequestDto requestDto){
        PermissionResponseDto permissionResponseDto = permissionServiceImp.createPermission(requestDto);
        return ApiResponseStructure.responseSuccess("Created", permissionResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getPermissions(){
        List<PermissionResponseDto> permissionResponseDtoList = permissionServiceImp.getAllPermissions();
        return ApiResponseStructure.responseSuccess("Ok", permissionResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{permissionId}")
    public ResponseEntity<Object> getPermissionById(@PathVariable Long permissionId){
        PermissionResponseDto permissionResponseDto = permissionServiceImp.getPermissionById(permissionId);
        return ApiResponseStructure.responseSuccess("Ok", permissionResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{permissionId}")
    public ResponseEntity<Object> updatePermission(@PathVariable Long permissionId, @Valid @RequestBody PermissionRequestDto requestDto){
        PermissionResponseDto permissionResponseDto = permissionServiceImp.updatePermission(permissionId, requestDto);
        return ApiResponseStructure.responseSuccess("Updated", permissionResponseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{permissionId}")
    public ResponseEntity<Object> deletePermission(@PathVariable Long permissionId){
        PermissionResponseDto permissionResponseDto = permissionServiceImp.deletePermission(permissionId);
        return ApiResponseStructure.responseSuccess("Deleted", permissionResponseDto, HttpStatus.OK);
    }
}
