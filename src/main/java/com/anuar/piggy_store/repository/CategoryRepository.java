package com.anuar.piggy_store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anuar.piggy_store.domain.Category;
import com.anuar.piggy_store.dto.response.CategoryDtoRes;
import com.anuar.piggy_store.dto.response.ProductDtoRes;

public interface CategoryRepository extends JpaRepository<Category,Long>  {

    @Query(value=
    "SELECT new com.anuar.piggy_store.dto.response.CategoryDtoRes"+
    "(c.id, c.name, c.type) " +
    "FROM Category c")
    List<CategoryDtoRes> findByPage(Pageable pageable);

    Optional<Category> findByName(String name);

    @Query("SELECT new com.anuar.piggy_store.dto.response.ProductDtoRes"+
    "(c.id, c.name, c.type, p.id, p.name, p.price, p.description, p.quantity) " +
    "FROM Category c JOIN c.products p " +
    "WHERE LOWER(c.name) = LOWER(:category)")
    List<ProductDtoRes> findByCategoryWithProducts(@Param("category") String category);
} 
