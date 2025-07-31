package com.anuar.piggy_store.specification;

import com.anuar.piggy_store.domain.Product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;

public class ProductSpecification {

    public static Specification<Product> hasName(String name) {
       
        return (root, query, cb) -> {

            Expression<String> lowerName = cb.lower(root.get("name"));

            return cb.like(
                    lowerName,
                    String.format("%%%s%%", name.toLowerCase()));

        };
    }

    public static Specification<Product> priceGreaterThan(Float minPrice) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(
                root.get("price"),
                minPrice);

    }

    public static Specification<Product> priceLessThan(Float maxPrice) {
        return (root, query, cb) -> {
            return cb.lessThanOrEqualTo(root.get("price"), maxPrice);
        };
    }

    public static Specification<Product> hasCategory(String category) {

        return (root, query, cb) -> {
            Expression<String> productjoinCategory = root.join("category").get("name");

            return cb.like(
                    cb.lower(productjoinCategory),
                    String.format("%%%s%%", category.toLowerCase()));
        };
    }

}
