package com.anuar.piggy_store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anuar.piggy_store.domain.Category;

public interface CategoryRepository extends JpaRepository<Category,Long>  {

    @Query(value="SELECT * FROM category LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Category> findByPage(@Param("limit") Long limit, @Param("offset") Long offset);
} 
