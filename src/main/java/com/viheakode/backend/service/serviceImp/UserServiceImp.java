package com.viheakode.backend.service.serviceImp;

import com.viheakode.backend.dto.request.UserModifyRequestDto;
import com.viheakode.backend.dto.request.UserRequestDto;
import com.viheakode.backend.dto.response.UserResponseDto;
import com.viheakode.backend.exception.ResourceDuplicateException;
import com.viheakode.backend.exception.ResourceNotFoundException;
import com.viheakode.backend.mapper.UserMapper;
import com.viheakode.backend.model.Role;
import com.viheakode.backend.model.User;
import com.viheakode.backend.repository.RoleRepository;
import com.viheakode.backend.repository.UserRepository;
import com.viheakode.backend.service.IUserService;
import com.viheakode.backend.util.SecurityUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImp implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @PreAuthorize("hasAuthority('CREATE_USER')")
    @Override
    public UserResponseDto createUser(UserRequestDto requestDto) {

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

    @PreAuthorize("hasAuthority('READ_USER')")
    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(UserMapper::toDto).toList();
    }

    @PreAuthorize("hasAuthority('READ_USER')")
    @Override
    public UserResponseDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return UserMapper.toDto(user);
    }

    @PreAuthorize("hasAuthority('UPDATE_USER')")
    @Override
    public UserResponseDto updateUser(Long userId, UserRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String username = requestDto.getUsername().toUpperCase();
        String email = requestDto.getEmail().toLowerCase();

        if (userRepository.existsByUsername(username)) throw new ResourceDuplicateException("User already exists");
        if (userRepository.existsByEmail(email)) throw new ResourceDuplicateException("Email already exists");

        user.setUsername(username);
        user.setEmail(email);
        user.setModifiedDate(LocalDateTime.now());
        userRepository.save(user);
        return UserMapper.toDto(user);
    }

    @PreAuthorize("hasAuthority('DELETE_USER')")
    @Override
    public UserResponseDto deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);

        return UserMapper.toDto(user);
    }

    @PreAuthorize("hasAuthority('MODIFY_USER')")
    @Override
    public UserResponseDto assignRolesToUser(Long userId, UserModifyRequestDto modifyRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Set<Role> roles = new HashSet<>(user.getRoles());
        for (String roleName : modifyRequestDto.getRoles()){
            Role role = roleRepository.findByRoleName(roleName.toUpperCase())
                    .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

            boolean exists = roles.stream()
                    .anyMatch(r -> r.getRoleId().equals(role.getRoleId()));
            if(exists){
                throw new ResourceDuplicateException("Role already assigned to user");
            }
            roles.add(role);

        }
        user.setRoles(roles);
        user.setModifiedDate(LocalDateTime.now());
        userRepository.save(user);
        return UserMapper.toDto(user);
    }

    @PreAuthorize("hasAuthority('MODIFY_USER')")
    @Override
    public UserResponseDto removeRolesFromUser(Long userId, UserModifyRequestDto modifyRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Set<Role> roles = new HashSet<>(user.getRoles());
        for (String roleName : modifyRequestDto.getRoles()){
            Role role = roleRepository.findByRoleName(roleName.toUpperCase())
                    .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

            boolean exists = roles.stream()
                    .anyMatch(r -> r.getRoleId().equals(role.getRoleId()));
            if(!exists){
                throw new ResourceNotFoundException("Role not assigned to user");
            }
            roles.removeIf(r -> r.getRoleId().equals(role.getRoleId()));

        }
        user.setRoles(roles);
        user.setModifiedDate(LocalDateTime.now());
        userRepository.save(user);
        return UserMapper.toDto(user);
    }
}
