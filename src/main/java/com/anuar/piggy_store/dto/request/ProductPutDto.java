package com.anuar.piggy_store.dto.request;

public record ProductPutDto(
        Long id,
        String name,
        Float price,
        String description,
        Long quantity,
        Long categoryId){}