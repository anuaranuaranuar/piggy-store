package com.anuar.piggy_store.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anuar.piggy_store.domain.Product;
import com.anuar.piggy_store.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getByPage(
            @RequestParam(required = true) Long page,
            @RequestParam(defaultValue = "30") Long size) {

        return service.getByPage(
            Long.valueOf(page),
            Long.valueOf(size));
    }

    @GetMapping("id/{id}")
    public Object getById(@PathVariable Long id) {
        return service.getByID(id);
    }

}
