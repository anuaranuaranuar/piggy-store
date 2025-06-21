package com.anuar.piggy_store.dto.response.request;

public record ProductDtoFakeStore(
    Long id, 
    String title, 
    float price, 
    String description,
    String category,
    String image,
    Rating rating
    ) {
}