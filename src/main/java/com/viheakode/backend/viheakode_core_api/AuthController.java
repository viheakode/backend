package com.viheakode.backend.viheakode_core_api;

import com.viheakode.backend.dto.request.LoginRequest;
import com.viheakode.backend.dto.request.UserRequestDto;
import com.viheakode.backend.dto.response.UserResponseDto;
import com.viheakode.backend.service.serviceImp.AuthServiceImp;
import com.viheakode.backend.util.ApiResponseStructure;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthServiceImp authServiceImp;

    public AuthController(AuthServiceImp authServiceImp) {
        this.authServiceImp = authServiceImp;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody LoginRequest loginRequest){
        String token = authServiceImp.authenticate(loginRequest);
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", token);
        return ResponseEntity.ok(stringMap);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody UserRequestDto requestDto){
        UserResponseDto userResponseDto = authServiceImp.register(requestDto);
        return ApiResponseStructure.responseSuccess("Register Successfully", userResponseDto, HttpStatus.CREATED);
    }
}
