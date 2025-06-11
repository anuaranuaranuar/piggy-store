package com.anuar.piggy_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anuar.piggy_store.domain.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

    
}