package com.anuar.piggy_store.controller;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
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
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service){
        this.service=service;
    }

    @GetMapping
    public List<CategoryDtoRes> getByPage(Pageable pageable
    ){
        return service.getByPage(pageable);
    }

    @GetMapping("/id/{id}")
    public Category getByID(@PathVariable Long id){
        return service.getByID(id).orElse(null);
    }

    @GetMapping("/{category}")
    public ApiResponse<List<ProductDtoRes>> getCategoryWithProducts(
        @PathVariable String category){
    List<ProductDtoRes> products = service.getByCategoryWithProducts(category);

    return new ApiResponse<List<ProductDtoRes>>(
        true,
        "Category:" + category,
        "SUCCESS",
        products,
        List.of());
        }

}
