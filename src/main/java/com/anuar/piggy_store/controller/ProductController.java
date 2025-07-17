package com.anuar.piggy_store.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
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
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> post(@RequestBody ProductPostDto productDto) {

        Product product = productService.save(productDto);

        URI location = URI.create("/products/" + product.getId());

        return ResponseEntity.created(location).body(product);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ProductDtoRes>>> getByPage(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Float minPrice,
            @RequestParam(required = false) Float maxPrice,
            @RequestParam(required = false) String category,
            Pageable pageable) {

        Page<ProductDtoRes> products = productService.getByPage(
                name,
                minPrice,
                maxPrice,
                category,
                pageable);

        ApiResponse<Page<ProductDtoRes>> response = new ApiResponse<>(
                true,
                "Exito",
                "SUCCESS",
                products,
                List.of());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getByID(id).orElse(null);
    }

    @PutMapping("/{id}")
    public void modify(@RequestParam Long id, @RequestBody ProductPostDto dto) {

        
    }

}
