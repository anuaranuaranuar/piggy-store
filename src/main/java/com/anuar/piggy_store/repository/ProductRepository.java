package com.anuar.piggy_store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anuar.piggy_store.domain.Product;
import com.anuar.piggy_store.dto.response.ProductDtoRes;

import jakarta.transaction.Transactional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value="SELECT * FROM product LIMIT :limit OFFSET :offset" ,nativeQuery=true)
    public List<Product> findByPage(@Param("limit") Long limit, @Param("offset") Long offset);
    
    @Query(value = 
    "SELECT new com.anuar.piggy_store.dto.response.ProductDtoRes" +
    "(p.id, p.name, p.price, p.description, p.quantity, c.name, c.type)" + 
    "FROM Product p JOIN p.category c "+
    "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    public List<ProductDtoRes> findByNameWithCategory(String name); 

    
}