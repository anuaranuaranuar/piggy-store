package com.anuar.piggy_store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anuar.piggy_store.domain.Product;
import com.anuar.piggy_store.dto.response.ProductDtoRes;

import jakarta.transaction.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long> {

        @Query(value = "SELECT new com.anuar.piggy_store.dto.response.ProductDtoRes" +
                        "(c.id, c.name, c.type, p.id, p.name, p.price, p.description, p.quantity) " +
                        "FROM Product p JOIN p.category c")
        public List<ProductDtoRes> findByPage(Pageable pageable);

        @Query(value = "SELECT new com.anuar.piggy_store.dto.response.ProductDtoRes" +
                        "(c.id, c.name, c.type, p.id, p.name, p.price, p.description, p.quantity) " +
                        "FROM Product p JOIN p.category c " +
                        "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
        public List<ProductDtoRes> findByNameWithCategory(@Param("name") String name);

        @Query(value = "SELECT new com.anuar.piggy_store.dto.response.ProductDtoRes" +
                        "(c.id, c.name, c.type, p.id, p.name, p.price, p.description, p.quantity) " +
                        "FROM Product p JOIN p.category c " +
                        "WHERE p.price BETWEEN :min AND :max")
        public List<ProductDtoRes> findByPriceWithCategory(
                        @Param("min") Float min,
                        @Param("max") Float max);

}