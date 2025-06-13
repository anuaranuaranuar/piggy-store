package com.anuar.piggy_store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anuar.piggy_store.domain.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value="SELECT * FROM product LIMIT :limit OFFSET :offset" ,nativeQuery=true)
    public List<Product> findByPage(@Param("limit") Long limit, @Param("offset") Long offset);
    
    
}