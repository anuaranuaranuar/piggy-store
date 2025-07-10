package com.anuar.piggy_store.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.anuar.piggy_store.domain.Category;
import com.anuar.piggy_store.domain.Product;
import com.anuar.piggy_store.dto.request.ProductPostDto;
import com.anuar.piggy_store.dto.response.ApiResponse;
import com.anuar.piggy_store.dto.response.ProductDtoRes;
import com.anuar.piggy_store.service.CategoryService;
import com.anuar.piggy_store.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;// hola

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public void post(@RequestBody ProductPostDto productDto) {
        productService.save(productDto);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductDtoRes>>> getByPage(Pageable pageable) {

        List<ProductDtoRes> products = productService.getByPage(pageable);

        ApiResponse<List<ProductDtoRes>> response = new ApiResponse<>(
            true,
            "Exito",
            "SUCCESS",
            products, 
            List.of()
        );

        return ResponseEntity.ok(response);
    }

    // http://localhost:8081/product?&&name
    // http://localhost:8081/product?page=2&size=3&sort=name,asc
    // http://localhost:8081/product?page=2&size=3&sort=name,desc
    @GetMapping("/id/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getByID(id).orElse(null);
    }

    // http://localhost:8081/product/name/ca
    @GetMapping("/name/{name}")
    public List<ProductDtoRes> getByName(@PathVariable String name) {
        return productService.getByNameWithCategory(name);
    }

    @GetMapping("/price/{min}/{max}")
    public ApiResponse<List<ProductDtoRes>> getByPriceWithCategory(
        @PathVariable Float min,
        @PathVariable Float max){
        
    List<ProductDtoRes> products = productService.getByPriceWithCategory(min, max);
         
    return new ApiResponse<>(
        true,
        "Exito",
        "success",
        products,
        List.of()
    );
    }

}
