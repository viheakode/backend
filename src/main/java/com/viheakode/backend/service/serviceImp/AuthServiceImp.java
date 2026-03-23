package com.viheakode.backend.service.serviceImp;

import com.viheakode.backend.dto.request.LoginRequest;
import com.viheakode.backend.dto.request.UserRequestDto;
import com.viheakode.backend.dto.response.UserResponseDto;
import com.viheakode.backend.exception.ResourceDuplicateException;
import com.viheakode.backend.exception.ResourceNotFoundException;
import com.viheakode.backend.mapper.UserMapper;
import com.viheakode.backend.model.Role;
import com.viheakode.backend.model.User;
import com.viheakode.backend.repository.RoleRepository;
import com.viheakode.backend.repository.UserRepository;
import com.viheakode.backend.util.SecurityUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class AuthServiceImp {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final MoyJwtService moyJwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImp(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService, MoyJwtService moyJwtService, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.moyJwtService = moyJwtService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String authenticate(LoginRequest loginRequest){

        String username = loginRequest.getUsername().toUpperCase();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username,
                loginRequest.getPassword())
        );
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        return moyJwtService.generateToken(userDetails);
    }

    public UserResponseDto register(UserRequestDto requestDto){
        String username = requestDto.getUsername().toUpperCase();
        String email = requestDto.getEmail().toLowerCase();

        if (userRepository.existsByUsername(username)) throw new ResourceDuplicateException("User already exists");
        if (userRepository.existsByEmail(email)) throw new ResourceDuplicateException("Email already exists");

        User user = new User();
        user.setUuid(UUID.randomUUID().toString());
        user.setUsername(username);
        user.setEmail(email);

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByRoleName("USER")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        roles.add(role);
        user.setRoles(roles);

        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setPublisher(SecurityUtil.getPublisher());
        userRepository.save(user);
        return UserMapper.toDto(user);
    }
}
