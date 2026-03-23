package com.viheakode.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequestDto {
    @NotBlank(message = "Role name is required")
    private String roleName;
    private String description;
}
