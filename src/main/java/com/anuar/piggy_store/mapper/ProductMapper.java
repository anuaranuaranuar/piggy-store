package com.anuar.piggy_store.mapper;

import com.anuar.piggy_store.dto.response.ProductDtoRes;
import com.anuar.piggy_store.dto.request.ProductPostDto;


import org.springframework.stereotype.Component;

import com.anuar.piggy_store.domain.Product;
import com.anuar.piggy_store.domain.Category;
import com.anuar.piggy_store.dto.request.FakeStoreProductDto;

@Component
public class ProductMapper {

    
    public Product toProduct(FakeStoreProductDto p){

        return new Product(
            null,
            p.title(),
            p.price(),
            p.description(),
            0L, //default value,
            null
            );
    }

    public ProductDtoRes toControllerDto(Product p){
        return new ProductDtoRes(
            p.getCategory().getId(),
            p.getCategory().getName(),
            p.getCategory().getType(),
            p.getId(),
            p.getName(),
            p.getPrice(),
            p.getDescription(),
            p.getQuantity());
    }

    public Product fromProductPostDto(ProductPostDto dto, Category category){
        return new Product(
                    null,
                    dto.name(),
                    dto.price(),
                    dto.description(),
                    dto.quantity(),
                    category);

    }
}
