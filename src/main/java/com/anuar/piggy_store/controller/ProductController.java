package com.anuar.piggy_store.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.anuar.piggy_store.domain.Product;
import com.anuar.piggy_store.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }
 @PostMapping("/create")
    public void post(@RequestBody Product product){
        service.save1(product);
    }
    @GetMapping
    public List<Product> getByPage(
            @RequestParam(required = true) Long page,
            @RequestParam(defaultValue = "30") Long size){

        return service.getByPage(
            Long.valueOf(page),
            Long.valueOf(size));
    }

    @GetMapping("/id/{id}")
    public Product getById(@PathVariable Long id) {
        return service.getByID(id).orElse(null);
    }
   
   
   
}
