package com.anuar.piggy_store.mainSpring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.anuar.piggy_store.domain.Category;
import com.anuar.piggy_store.domain.Product;
import com.anuar.piggy_store.dto.request.FakeStoreProductDto;
import com.anuar.piggy_store.external.api.FakeStoreApi;
import com.anuar.piggy_store.mapper.MapperProduct;
import com.anuar.piggy_store.service.CategoryService;
import com.anuar.piggy_store.service.ProductService;

@Component
public class MainSpring implements CommandLineRunner {

    

    @Override
    public void run(String... args) throws Exception {
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");// System.out.println(fakeStoreApi.getProducts());

       /*  List<FakeStoreProductDto> productsDto = fakeStoreApi.getProducts();

        List<Product> products = new ArrayList<Product>();

        for (FakeStoreProductDto dto : productsDto) {
            Product product = mapperProduct.toProduct(dto);


            System.out.println(product);
            Category category = categoryService.getByName(dto.category())
                    .orElse(null);
            
            System.out.println(category);
            
            product.setCategory(category); 
            
            products.add(product);
        }

        System.out.println(productService.saveAll(products));

 */
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    }

}
