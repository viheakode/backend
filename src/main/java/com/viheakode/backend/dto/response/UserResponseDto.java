package com.viheakode.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UserResponseDto {
    private Long userId;
    private String uuid;
    private String username;
    private String email;
    private List<String> roles;
    private String status;
    private String publisher;
    private LocalDateTime publishedDate;
    private LocalDateTime modifiedDate;
}
