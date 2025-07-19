package com.anuar.piggy_store.mapper;

import org.springframework.stereotype.Component;

import com.anuar.piggy_store.domain.Category;
import com.anuar.piggy_store.dto.request.CategoryDto;
import com.anuar.piggy_store.dto.response.CategoryDtoRes;

@Component
public class CategoryMapper {

    public CategoryDtoRes toCategoryDtoRes(Category category){
        return new CategoryDtoRes(
        category.getId(),
        category.getName(),
        category.getType()
        );
    }

    public Category fromCategoryDto(CategoryDto dto) {
        return new Category(
            null,
            dto.name(),
            dto.type(),
            null
        );
    }

    
}
