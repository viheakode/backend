package com.viheakode.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PermissionResponseDto {
    private Long permissionId;
    private String uuid;
    private String permissionName;
    private String description;
    private String status;
    private String publisher;
    private LocalDateTime publishedDate;
    private LocalDateTime modifiedDate;
}
