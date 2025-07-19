package com.anuar.piggy_store.specification;

import org.springframework.data.jpa.domain.Specification;

import com.anuar.piggy_store.domain.Category;

import jakarta.persistence.criteria.Expression;

public class CategorySpecification {

    public static Specification<Category> hasCategory(String name) {

        return (root, query, cb) -> {
            Expression<String> lowerName = cb.lower(root.get("name"));

            return cb.like(
                    lowerName,
                    String.format("%%%s%%", name.toLowerCase()));
        };
    }

    public static Specification<Category> hasType(String type) {

        return (root, query, cb) -> {
            Expression<String> lowerType = cb.lower(root.get("type"));

            return cb.like(
                    lowerType,
                    String.format("%%%s%%", type.toLowerCase()));
        };
    }
}
