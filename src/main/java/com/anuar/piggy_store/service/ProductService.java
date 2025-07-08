package com.anuar.piggy_store.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.anuar.piggy_store.domain.Category;
import com.anuar.piggy_store.domain.Product;
import com.anuar.piggy_store.dto.request.ProductPostDto;
import com.anuar.piggy_store.dto.response.ApiResponse;
import com.anuar.piggy_store.dto.response.ProductDtoRes;
import com.anuar.piggy_store.repository.CategoryRepository;
import com.anuar.piggy_store.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product save(ProductPostDto dto) {
        Optional<Category> category = categoryRepository.findById(dto.categoryId());

        if (category.isPresent()) {

            return productRepository.save(new Product(
                    null,
                    dto.name(),
                    dto.price(),
                    dto.description(),
                    dto.quantity(),
                    category.orElse(null)));
        }

        return null;
    }

    // trae lista de productos por pagina
    public List<ProductDtoRes> getByPage(Pageable pageable) {
        return productRepository.findByPage(pageable);
    }

    public Optional<Product> getByID(long id) {

        return productRepository.findById(id);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<Product> saveAll(List<Product> products) {
        return productRepository.saveAll(products);
    }

    public List<ProductDtoRes> getByNameWithCategory(String name) {
        return productRepository.findByNameWithCategory(name);
    }

    public List<ProductDtoRes> getByPriceWithCategory(Float min, Float max){
        return productRepository.findByPriceWithCategory(min, max);
    }

}
