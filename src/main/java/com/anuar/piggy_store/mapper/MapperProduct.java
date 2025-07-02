package com.anuar.piggy_store.mapper;

import org.springframework.stereotype.Component;

import com.anuar.piggy_store.domain.Product;
import com.anuar.piggy_store.dto.request.FakeStoreProductDto;

@Component
public class MapperProduct {

    
    public Product toProduct(FakeStoreProductDto p){

        
        return new Product(
            p.title(),
            p.price(),
            p.description(),
           0L //default value
            );

    }
}
