package com.anuar.piggy_store.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDto(
        @NotBlank
        String name,

        @NotNull
        Float price,

        @NotBlank
        String description,

        @NotNull
        Long quantity,
        
        @NotNull
        Long categoryId){}
