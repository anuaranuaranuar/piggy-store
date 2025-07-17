package com.anuar.piggy_store.service;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.boot.autoconfigure.rsocket.RSocketProperties.Server.Spec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.anuar.piggy_store.domain.Category;
import com.anuar.piggy_store.domain.Product;
import com.anuar.piggy_store.dto.request.ProductPostDto;
import com.anuar.piggy_store.dto.request.ProductPutDto;
import com.anuar.piggy_store.dto.response.ApiResponse;
import com.anuar.piggy_store.dto.response.ProductDtoRes;
import com.anuar.piggy_store.mapper.ProductMapper;
import com.anuar.piggy_store.repository.CategoryRepository;
import com.anuar.piggy_store.repository.ProductRepository;
import com.anuar.piggy_store.specification.ProductSpecification;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository,
            ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    // trae lista de productos por pagina
    @Transactional(readOnly = true)
    public Page<ProductDtoRes> getByPage(
            String name,
            Float minPrice,
            Float maxPrice,
            String category,
            Pageable pageable) {

        Specification<Product> spec = (root, query, cb) -> cb.conjunction();

        if (StringUtils.hasText(name)) {
            spec = spec.and(ProductSpecification.hasName(name));
        }

        if (minPrice != null && minPrice != 0) {
            spec = spec.and(ProductSpecification.priceGreaterThan(minPrice));
        }

        if (maxPrice != null && maxPrice != 0) {
            spec = spec.and(ProductSpecification.priceLessThan(maxPrice));
        }

        if (StringUtils.hasText(category)) {
            spec = spec.and(ProductSpecification.hasCategory(category));
        }

        Page<ProductDtoRes> result = productRepository.findAll(spec, pageable)
                .map(productMapper::toControllerDto);

        if (result.isEmpty()) {
            // todo crear excepcion
            throw new IllegalArgumentException("datos no encontrados");

        }

        return result;
    }

    public Product save(ProductPostDto dto) {
        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> // todo crear excepcion
                new IllegalArgumentException("Category not found"));

        Product product = productMapper.fromProductPostDto(dto, category);

        return product = productRepository.save(product);
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

    @Transactional
    public void modify(Long id,  ProductPutDto dto) {
       Optional<Product> product = productRepository.findById((dto.id()));
        
        
       }

    }


