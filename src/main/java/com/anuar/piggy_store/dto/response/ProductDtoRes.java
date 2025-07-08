package com.anuar.piggy_store.dto.response;

import com.anuar.piggy_store.domain.Category;

public record ProductDtoRes(
    Long categoryId,
    String categoryName,
    String categoryType,
    Long id,
    String name,
    float price,
    String description,
    Long quantity
) {}