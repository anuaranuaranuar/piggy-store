package com.anuar.piggy_store.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record ProductPostDto(
        Long id,
        String name,
        Float price,
        String description,
        Long quantity,
        Long categoryId){}
