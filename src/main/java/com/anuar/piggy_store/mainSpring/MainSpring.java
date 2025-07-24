package com.anuar.piggy_store.mainSpring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.anuar.piggy_store.domain.Category;
import com.anuar.piggy_store.domain.Product;
import com.anuar.piggy_store.dto.request.CategoryDto;
import com.anuar.piggy_store.dto.request.FakeStoreProductDto;
import com.anuar.piggy_store.dto.request.ProductDto;
import com.anuar.piggy_store.dto.response.CategoryDtoRes;
import com.anuar.piggy_store.external.api.FakeStoreApi;
import com.anuar.piggy_store.mapper.CategoryMapper;
import com.anuar.piggy_store.mapper.ProductMapper;
import com.anuar.piggy_store.service.CategoryService;
import com.anuar.piggy_store.service.ProductService;

@Component
public class MainSpring implements CommandLineRunner {
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;
    private final FakeStoreApi fakeStoreApi;
    private final CategoryService categoryService;
    private final ProductService productService;

    public MainSpring(ProductMapper productMapper, CategoryMapper categoryMapper, FakeStoreApi fakeStoreApi,
            CategoryService categoryService, ProductService productService) {
        this.productMapper = productMapper;
        this.categoryMapper = categoryMapper;
        this.fakeStoreApi = fakeStoreApi;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");// System.out.println(fakeStoreApi.getProducts());

       /*  List<String> categoriesFake = fakeStoreApi.getCategory();

        for (String categoryFake : categoriesFake) {

            CategoryDto category = new CategoryDto(
                    categoryFake,
                    null);

            categoryService.save(category);
        }

        List<FakeStoreProductDto> productsDto = fakeStoreApi.getProducts();

        List<Product> products = new ArrayList<Product>();

        var page = Pageable.unpaged();

        for (FakeStoreProductDto dto : productsDto) {

            CategoryDto categoryDto = new CategoryDto(dto.category(), null);

            CategoryDtoRes categoryDtoRes = categoryService.getByPage(categoryDto, page)
                    .getContent().getFirst();

                    ProductDto productDto = new ProductDto(
                        dto.title(), 
                        dto.price(), 
                        dto.description(), 
                        100L,
                        categoryDtoRes.id()
                    );

                    Category category = categoryMapper.fromCategoryDtoRes(categoryDtoRes);
            
           Product product = productMapper.fromProductDto(productDto, category);

            products.add(product);
        }

        System.out.println(productService.saveAll(products));*/

        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    }
}