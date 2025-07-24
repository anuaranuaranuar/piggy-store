package com.anuar.piggy_store.service;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.boot.autoconfigure.rsocket.RSocketProperties.Server.Spec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.anuar.piggy_store.domain.Category;
import com.anuar.piggy_store.domain.Product;
import com.anuar.piggy_store.dto.filter.ProductFilter;
import com.anuar.piggy_store.dto.request.ProductDto;
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
    public Page<ProductDtoRes> getByPage(ProductFilter filter, Pageable pageable) {

        Specification<Product> spec = (root, query, cb) -> cb.conjunction();

        if (StringUtils.hasText(filter.name())) {
            spec = spec.and(ProductSpecification.hasName(filter.name()));
        }

        if (filter.minPrice() != null && filter.minPrice() != 0) {
            spec = spec.and(ProductSpecification.priceGreaterThan(filter.minPrice()));
        }

        if (filter.maxPrice() != null && filter.maxPrice() != 0) {
            spec = spec.and(ProductSpecification.priceLessThan(filter.maxPrice()));
        }

        if (StringUtils.hasText(filter.category())) {
            spec = spec.and(ProductSpecification.hasCategory(filter.category()));
        }

        Page<ProductDtoRes> result = productRepository.findAll(spec, pageable)
                .map(productMapper::toControllerDto);

        if (result.isEmpty()) {
            // TODO crear excepcion
            throw new IllegalArgumentException("data not found");
        }

        return result;
    }

    public ProductDtoRes save(ProductDto dto) {
        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> // TODO crear excepcion
                new IllegalArgumentException("Category not found"));

        Product product = productMapper.fromProductDto(dto, category);

        product = productRepository.save(product);

        return productMapper.toControllerDto(product);
    }

    @Transactional(readOnly = true)
    public ProductDtoRes getByID(long id) {

        var product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        return productMapper.toControllerDto(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<Product> saveAll(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Transactional
    public ProductDtoRes update(Long id, ProductDto dto) {
        Product product = productRepository.findById((id))
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> // TODO crear excepcion
                new IllegalArgumentException("Category not found"));

        productMapper.updateProductFromDto(product, dto, category);

        productRepository.save(product);

        return productMapper.toControllerDto(product);
    }

    @Transactional
    public ProductDtoRes remove(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product Not Fount"));

        if (!product.getIsActive()) {
            throw new IllegalArgumentException("Product is disable");
        }
        product.setIsActive(false);

        productRepository.save(product);

        return productMapper.toControllerDto(product);
    }

}
