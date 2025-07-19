package com.anuar.piggy_store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.anuar.piggy_store.domain.Category;
import com.anuar.piggy_store.dto.response.CategoryDtoRes;
import com.anuar.piggy_store.dto.response.ProductDtoRes;
import com.anuar.piggy_store.mapper.CategoryMapper;
import com.anuar.piggy_store.repository.CategoryRepository;
import com.anuar.piggy_store.specification.CategorySpecification;

import ch.qos.logback.core.util.StringUtil;

@Service
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository repository, CategoryMapper categoryMapper) {
        this.repository = repository;
        this.categoryMapper = categoryMapper;
    }

    @Transactional(readOnly = true)
    public Page<CategoryDtoRes> getByPage(String category, Pageable pageable) {
        Specification<Category> spec = (root, query, cb) -> cb.conjunction();

        if (StringUtils.hasText(category)) {
            spec = spec.and(CategorySpecification.hasCategory(category));
        }

        return repository.findAll(spec, pageable)
                .map(categoryMapper::toCategoryDtoRes);
    }

    @Transactional(readOnly = true)
    public CategoryDtoRes getByID(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category Not Fount"));
        
            return categoryMapper.toCategoryDtoRes(category);
    }

    public Boolean save(Category category) {
        if (category.getName() == null) {

            return false;
        }
        repository.save(category);
        return true;
    }

    public List<Category> saveAll(List<Category> categories) {
        return repository.saveAll(categories);
    }

}