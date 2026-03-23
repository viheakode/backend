package com.viheakode.backend.viheakode_core_api;

import com.viheakode.backend.dto.request.UserModifyRequestDto;
import com.viheakode.backend.dto.request.UserRequestDto;
import com.viheakode.backend.dto.response.UserResponseDto;
import com.viheakode.backend.service.serviceImp.UserServiceImp;
import com.viheakode.backend.util.ApiResponseStructure;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServiceImp userServiceImp;

    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserRequestDto requestDto){
        UserResponseDto userResponseDto = userServiceImp.createUser(requestDto);
        return ApiResponseStructure.responseSuccess("Created", userResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers(){
        List<UserResponseDto> userResponseDtoList = userServiceImp.getAllUsers();
        return ApiResponseStructure.responseSuccess("Ok", userResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable Long userId){
        UserResponseDto userResponseDto = userServiceImp.getUserById(userId);
        return ApiResponseStructure.responseSuccess("Ok", userResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable Long userId, @Valid @RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto = userServiceImp.updateUser(userId, userRequestDto);
        return ApiResponseStructure.responseSuccess("Updated", userResponseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long userId){
        UserResponseDto userResponseDto = userServiceImp.deleteUser(userId);
        return ApiResponseStructure.responseSuccess("Deleted", userResponseDto, HttpStatus.OK);
    }

    @PostMapping("/{userId}/roles")
    public ResponseEntity<Object> assignRolesToUser(@PathVariable Long userId, @Valid @RequestBody UserModifyRequestDto userModifyRequestDto){
        UserResponseDto userResponseDto = userServiceImp.assignRolesToUser(userId, userModifyRequestDto);
        return ApiResponseStructure.responseSuccess("Created", userResponseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}/roles")
    public ResponseEntity<Object> removeRolesFromUser(@PathVariable Long userId, @Valid @RequestBody UserModifyRequestDto userModifyRequestDto){
        UserResponseDto userResponseDto = userServiceImp.removeRolesFromUser(userId, userModifyRequestDto);
        return ApiResponseStructure.responseSuccess("Deleted", userResponseDto, HttpStatus.OK);
    }
}
