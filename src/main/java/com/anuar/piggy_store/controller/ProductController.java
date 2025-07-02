package com.anuar.piggy_store.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.anuar.piggy_store.domain.Category;
import com.anuar.piggy_store.domain.Product;
import com.anuar.piggy_store.dto.response.ProductDtoRes;
import com.anuar.piggy_store.service.CategoryService;
import com.anuar.piggy_store.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    

   public ProductController(ProductService productService) {
        this.productService = productService;
    }

 @PostMapping("/create")
    public void post(@RequestBody Product product){
        productService.save(product);
    }
    @GetMapping
    public List<Product> getByPage(
            @RequestParam(required = true) Long page,
            @RequestParam(defaultValue = "30") Long size){

        return productService.getByPage(
            Long.valueOf(page),
            Long.valueOf(size));
    }

    @GetMapping("/id/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getByID(id).orElse(null);
    }

     @GetMapping("/name/{name}")
    public List<ProductDtoRes> getByname(@PathVariable String name) {
    
      return productService.getBynameWithCategory(name);
    }
   
   
   
}
