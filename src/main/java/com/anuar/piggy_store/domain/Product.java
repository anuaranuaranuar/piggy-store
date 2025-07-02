package com.anuar.piggy_store.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
public class Product{
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@Column(nullable = false)
    private String name;

	@Column(nullable = false)
    private Float price;

    @Column(columnDefinition = "TEXT")
    private String description;
    
    private Long quantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;


	public Product(){}
    
	public Product(String name, Float price, String description, Long quantity) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
	}

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

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description
                + ", quantity=" + quantity + "]";
    }

	
	
    
}

