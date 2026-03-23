package com.viheakode.backend.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleModifyRequestDto {
    @NotEmpty(message = "Permissions can not be empty")
    private List<String> permissions;
}
