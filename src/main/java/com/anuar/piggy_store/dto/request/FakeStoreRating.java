package com.anuar.piggy_store.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FakeStoreRating(
    @JsonProperty("rate")
    float rate,

    @JsonProperty("count")
    int count
) {
} 