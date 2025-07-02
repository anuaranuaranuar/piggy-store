package com.anuar.piggy_store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anuar.piggy_store.domain.Product;
import com.anuar.piggy_store.dto.response.ProductDtoRes;
import com.anuar.piggy_store.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository=repository;
    }

    public Boolean save(Product p){
        if(p.getName() == null || p.getPrice() == null){
             log.warn("Intento de guardar producto inválido: {}", p);
            return false;
        }
        repository.save(p);
        return true;
    }
    
    //trae  lista de productos  por pagina 
    public List<Product> getByPage(Long page, Long numberRows){
        if(numberRows>50){//limito las filas 
            numberRows = (long) 50;
        }

        Long offset = (page-1)*numberRows;
        return repository.findByPage(numberRows, offset);
    }
    
    public Optional<Product> getByID(long id){
         
        return repository.findById(id);
    }
    
    public List<Product> getAll(){
        return repository.findAll();
    }

    public List<Product> saveAll(List<Product> products) {
        return repository.saveAll(products);
    }

    public List<ProductDtoRes> getBynameWithCategory(String name) {
        return repository.findByNameWithCategory(name);
    }
    
}
