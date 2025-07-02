package com.anuar.piggy_store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anuar.piggy_store.domain.Category;
import com.anuar.piggy_store.dto.response.CategoryDtoRes;

public interface CategoryRepository extends JpaRepository<Category,Long>  {

    @Query(value=
    "SELECT new com.anuar.piggy_store.dto.response.CategoryDtoRes"+
    "(c.id, c.name, c.type) " +
    "FROM Category c")
    List<CategoryDtoRes> findByPage(org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable pageable);

    Optional<Category> findByName(String name);
} 
