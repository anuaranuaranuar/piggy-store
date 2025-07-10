package com.anuar.piggy_store.domain;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter @Getter
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

    
   
    public Category(String name, String type) {
        this.name = name;
        this.type = type;
    }
   
    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + ", type=" + type +"]";
    }

   
    
}
