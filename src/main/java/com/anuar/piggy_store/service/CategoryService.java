package com.anuar.piggy_store.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.anuar.piggy_store.domain.Category;
import com.anuar.piggy_store.dto.request.CategoryDto;
import com.anuar.piggy_store.dto.response.CategoryDtoRes;
import com.anuar.piggy_store.dto.response.ProductDtoRes;
import com.anuar.piggy_store.mapper.CategoryMapper;
import com.anuar.piggy_store.repository.CategoryRepository;
import com.anuar.piggy_store.specification.CategorySpecification;

import ch.qos.logback.core.util.StringUtil;
import jakarta.validation.Valid;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Transactional(readOnly = true)
    public Page<CategoryDtoRes> getByPage(CategoryDto dto, Pageable pageable) {
        Specification<Category> spec = (root, query, cb) -> cb.conjunction();

        if (StringUtils.hasText(dto.name())) {
            spec = spec.and(CategorySpecification.hasCategory(dto.name()));
        }
        if (StringUtils.hasText(dto.type())) {
            spec = spec.and(CategorySpecification.hasType(dto.type()));
        }

        return categoryRepository.findAll(spec, pageable)
                .map(categoryMapper::toCategoryDtoRes);
    }

    @Transactional(readOnly = true)
    public CategoryDtoRes getByID(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category Not Fount"));

        return categoryMapper.toCategoryDtoRes(category);
    }

    public CategoryDtoRes save(CategoryDto dto) {
        Category category = categoryMapper.fromCategoryDto(dto);

        category = categoryRepository.save(category);

        return categoryMapper.toCategoryDtoRes(category);

    }

    public CategoryDtoRes update(Long id, CategoryDto dto) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("CategoryNotFount"));

        categoryMapper.update(category, dto);

        category = categoryRepository.save(category);

        return categoryMapper.toCategoryDtoRes(category);
    }
}