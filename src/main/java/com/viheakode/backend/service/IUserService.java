package com.viheakode.backend.service;

import com.viheakode.backend.dto.request.UserModifyRequestDto;
import com.viheakode.backend.dto.request.UserRequestDto;
import com.viheakode.backend.dto.response.UserResponseDto;

import java.util.List;

public interface IUserService {
    UserResponseDto createUser(UserRequestDto requestDto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(Long userId);
    UserResponseDto updateUser(Long userId, UserRequestDto requestDto);
    UserResponseDto deleteUser(Long userId);

    UserResponseDto assignRolesToUser(Long userId, UserModifyRequestDto modifyRequestDto);
    UserResponseDto removeRolesFromUser(Long userId, UserModifyRequestDto modifyRequestDto);
}
