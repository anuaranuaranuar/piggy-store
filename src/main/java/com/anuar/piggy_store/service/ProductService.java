package com.anuar.piggy_store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anuar.piggy_store.domain.Product;
import com.anuar.piggy_store.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository=repository;
    }


    public Optional<Product> getProductByID(long id){
        return repository.findById(id);
    }

    public List<Product> getProducts(){
        return repository.findAll(); 
    }

    public void deleteProductById(Long id){
        repository.deleteById(id);
    }
}
