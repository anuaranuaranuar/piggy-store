package com.anuar.piggy_store.controller;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anuar.piggy_store.domain.Category;
import com.anuar.piggy_store.domain.Product;
import com.anuar.piggy_store.dto.request.CategoryDto;
import com.anuar.piggy_store.dto.response.ApiResponse;
import com.anuar.piggy_store.dto.response.CategoryDtoRes;
import com.anuar.piggy_store.dto.response.ProductDtoRes;
import com.anuar.piggy_store.service.CategoryService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CategoryDtoRes>>> getByPage(
        @ModelAttribute CategoryDto category,
        Pageable pageable) {
        Page<CategoryDtoRes> categories = categoryService.getByPage(category, pageable);

        var response = new ApiResponse<>(
                true,
                "exito",
                "SUCCESS",
                categories,
                null);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public CategoryDtoRes getByID(@PathVariable Long id) {
        return categoryService.getByID(id);
    }

    @PostMapping
    public ResponseEntity<CategoryDtoRes> create(@RequestBody @Valid CategoryDto category){
        CategoryDtoRes response = categoryService.save(category);

        URI location = URI.create("/categories/" + response.id());

        return ResponseEntity.created(location).body(response);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDtoRes> update(
    @PathVariable Long id,   
    @RequestBody @Valid CategoryDto category){
        
        return ResponseEntity.ok(categoryService.update(id, category));
        
    }

    

}
