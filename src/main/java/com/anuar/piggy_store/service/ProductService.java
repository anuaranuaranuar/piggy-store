package com.anuar.piggy_store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anuar.piggy_store.domain.Product;
import com.anuar.piggy_store.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository=repository;
    }

    public void save1(Product p){
        repository.save1(
            p.getName(),
            p.getDescription(),
            p.getPrice(),
            p.getQuantity(),
            p.getCategory().getId());
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
    
    
}
