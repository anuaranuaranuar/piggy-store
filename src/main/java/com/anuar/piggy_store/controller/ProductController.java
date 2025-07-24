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
import com.anuar.piggy_store.dto.filter.ProductFilter;
import com.anuar.piggy_store.dto.request.ProductDto;
import com.anuar.piggy_store.dto.response.ApiResponse;
import com.anuar.piggy_store.dto.response.ProductDtoRes;
import com.anuar.piggy_store.mapper.ProductMapper;
import com.anuar.piggy_store.service.CategoryService;
import com.anuar.piggy_store.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDtoRes> post(@RequestBody @Valid ProductDto productDto) {

        ProductDtoRes product = productService.save(productDto);

        URI location = URI.create("/products/" + product.id());

        return ResponseEntity.created(location).body(product);

    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ProductDtoRes>>> getByPage(
            @ModelAttribute ProductFilter filter,
            Pageable pageable) {

        Page<ProductDtoRes> products = productService.getByPage(filter, pageable);

        ApiResponse<Page<ProductDtoRes>> response = new ApiResponse<>(
                true,
                "Exito",
                "SUCCESS",
                products,
                List.of());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ProductDtoRes getById(@PathVariable Long id) {
        return productService.getByID(id);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDtoRes> update(@PathVariable Long id, @RequestBody @Valid ProductDto dto) {

        return ResponseEntity.ok(productService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDtoRes> remove(@PathVariable Long id){
        return ResponseEntity.ok(productService.remove(id));
    }

}
