package com.anuar.piggy_store.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FakeStoreProductDto(
    @JsonProperty("id")
    Long id, 
    
    @JsonProperty("title")
    String title, 
    
    @JsonProperty("price")
    Float price, 
    
    @JsonProperty("description")
    String description,
    
    @JsonProperty("category")
    String category,
    
    @JsonProperty("image")
    String image,
    
    @JsonProperty("rating")
    FakeStoreRating rating
    ) {
}