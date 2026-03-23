package com.viheakode.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionRequestDto {
    @NotBlank(message = "Permission name is required")
    private String permissionName;
    private String description;
}
