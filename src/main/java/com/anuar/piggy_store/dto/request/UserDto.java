package com.anuar.piggy_store.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDto(
    
    @Email
    @NotBlank
    String email,

    @NotBlank
    String password
) {
} 