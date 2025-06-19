package com.anuar.piggy_store.domain;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Category {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    
    @OneToMany(
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY,
        mappedBy = "category",
        orphanRemoval = true)
    private List<Product> products;

    
    public Category(long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
   
    public Category(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

   
    
}
