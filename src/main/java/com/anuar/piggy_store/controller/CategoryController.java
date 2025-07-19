package com.anuar.piggy_store.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anuar.piggy_store.domain.Category;
import com.anuar.piggy_store.domain.Product;
import com.anuar.piggy_store.dto.response.ApiResponse;
import com.anuar.piggy_store.dto.response.CategoryDtoRes;
import com.anuar.piggy_store.dto.response.ProductDtoRes;
import com.anuar.piggy_store.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CategoryDtoRes>>> getByPage(@RequestParam(required = false) String category, Pageable pageable) {
        Page<CategoryDtoRes> categories = service.getByPage(category, pageable);

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
        return service.getByID(id);
    }

    

}
