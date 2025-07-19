package com.anuar.piggy_store.specification;


import org.springframework.data.jpa.domain.Specification;

import com.anuar.piggy_store.domain.Category;

import jakarta.persistence.criteria.Expression;

public class CategorySpecification {

    public static Specification<Category> hasCategory(String category) {

        return (root, query, cb) -> {
            Expression<String> lower = cb.lower(root.get("name"));
            
            return cb.like(
                    lower,
                    "%".concat(category).concat("%"));
        };
    }
}
