package com.anuar.piggy_store.mapper;

import com.anuar.piggy_store.dto.request.ProductDto;
import com.anuar.piggy_store.dto.response.CategoryDtoRes;
import com.anuar.piggy_store.dto.response.ProductDtoRes;

import org.springframework.stereotype.Component;

import com.anuar.piggy_store.domain.Product;
import com.anuar.piggy_store.domain.Category;
import com.anuar.piggy_store.dto.request.FakeStoreProductDto;

@Component
public class ProductMapper {
    private final CategoryMapper categoryMapper;

    public ProductMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

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

    public Product updateProductFromDto(Product product, ProductDto dto, Category category){
        product.setName(dto.name());
        product.setPrice(dto.price());
        product.setQuantity(dto.quantity());
        product.setDescription(dto.description());
        product.setCategory(category);

        return product;
    }


    public ProductDtoRes toControllerDto(Product p){

        CategoryDtoRes CategoryDtoRes = categoryMapper.toCategoryDtoRes(p.getCategory());
        
        return new ProductDtoRes(
            CategoryDtoRes,
            p.getId(),
            p.getName(),
            p.getPrice(),
            p.getDescription(),
            p.getQuantity());
    }

    public Product fromProductDto(ProductDto dto, Category category){
        return new Product(
                    null,
                    dto.name(),
                    dto.price(),
                    dto.description(),
                    dto.quantity(),
                    category);

    }

}
