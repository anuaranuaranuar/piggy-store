package com.anuar.piggy_store.dto.filter;

import org.springframework.data.domain.Pageable;

public record ProductFilter(
        String name,
        Float minPrice,
        Float maxPrice,
        String category) {

}
