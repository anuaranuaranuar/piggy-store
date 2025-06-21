package com.anuar.piggy_store.external.api;

import java.net.URI;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.anuar.piggy_store.dto.response.request.ProductDtoFakeStore;

@Component
public class FakeStoreApi {
    private final WebClient webClient;

    public FakeStoreApi(WebClient webClient){
        this.webClient=webClient;
    } 

public List<ProductDtoFakeStore> getProducts(){
    
    return webClient.get()
            .uri(URI
                .create("https://fakestoreapi.com/products/"))
            .retrieve()
            .bodyToFlux(ProductDtoFakeStore.class)
            .collectList()
            .block();
}

public String getCategory(){ 
    
    return webClient.get()
        .uri(URI
        .create("https://fakestoreapi.com/products/categories"))
        .retrieve()
        .bodyToMono(String.class)
        .block();
    

}

}