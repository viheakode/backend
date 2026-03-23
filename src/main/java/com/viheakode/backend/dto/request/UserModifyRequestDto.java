package com.viheakode.backend.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserModifyRequestDto {
    @NotEmpty(message = "Roles can not be empty")
    private List<String> roles;
}
