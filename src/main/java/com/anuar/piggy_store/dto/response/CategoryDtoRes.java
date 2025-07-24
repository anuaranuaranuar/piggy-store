package com.anuar.piggy_store.dto.response;

public record CategoryDtoRes(
    Long id,
    String name,
    String type,
    Boolean isActive
) {
    
}
