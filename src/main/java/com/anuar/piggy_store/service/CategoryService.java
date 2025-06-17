package com.anuar.piggy_store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anuar.piggy_store.domain.Category;
import com.anuar.piggy_store.repository.CategoryRepository;

@Service
public class CategoryService {
    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository){
        this.repository=repository;
    }

    public List<Category> getByPage(Long page, Long numberRows){
        if(numberRows> 30){
            numberRows = (long)30;
        }
        Long offset = (page-1)*numberRows;

        return repository.findByPage(numberRows,offset);
    }

    public Optional<Category> getByID(Long id){
        return repository.findById(id);
    }

}