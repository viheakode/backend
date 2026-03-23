package com.viheakode.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class RoleResponseDto {
    private Long roleId;
    private String uuid;
    private String roleName;
    private String description;
    private List<String> permissions;
    private String status;
    private String publisher;
    private LocalDateTime publishedDate;
    private LocalDateTime modifiedDate;
}
