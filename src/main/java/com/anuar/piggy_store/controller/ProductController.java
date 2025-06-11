package com.anuar.piggy_store.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anuar.piggy_store.service.ProductService;


@RestController
@RequestMapping("products")
public class ProductController {
   private final ProductService service;

    public ProductController(ProductService service){
        this.service=service;
    }

    @GetMapping
    public Object getProducts(){
        return service.getProducts();
      
    }

    @GetMapping("{id}")
    public Object getProductById(@PathVariable Long id){
        return service.getProductByID(id);
    }

    @GetMapping("delete/{id}")
    public void deleteProductById(@PathVariable Long id){
        service.deleteProductById(id);
    }
    
}
