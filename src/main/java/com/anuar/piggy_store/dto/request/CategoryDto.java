package com.anuar.piggy_store.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryDto(
    @NotBlank
    String name,

    String type
) {
}