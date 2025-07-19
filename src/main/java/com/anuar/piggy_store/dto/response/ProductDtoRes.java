package com.anuar.piggy_store.dto.response;

import com.anuar.piggy_store.dto.response.CategoryDtoRes;

public record ProductDtoRes(
    CategoryDtoRes category,
    Long id,
    String name,
    float price,
    String description,
    Long quantity
) {}