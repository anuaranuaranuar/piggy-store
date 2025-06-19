package com.anuar.piggy_store.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anuar.piggy_store.domain.Category;
import com.anuar.piggy_store.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service){
        this.service=service;
    }
    

    @GetMapping
    public List<Category> getByPage(
        @RequestParam(required = true) Long page,
        @RequestParam(defaultValue = "30") Long size
    ){
        return service.getByPage(page, size);
    }

    @GetMapping("id/{id}")
    public Category getByID(@PathVariable Long id){
        return service.getByID(id).orElse(null);
    }
    ppppppppphola
}
