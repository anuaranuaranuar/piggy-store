package com.anuar.piggy_store.external.api;

import java.net.URI;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.anuar.piggy_store.dto.request.FakeStoreProductDto;

@Component
public class FakeStoreApi {
    private final WebClient webClient;

    public FakeStoreApi(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<FakeStoreProductDto> getProducts() {

        return webClient.get()
                .uri("https://fakestoreapi.com/products/")
                .retrieve()
                .bodyToFlux(FakeStoreProductDto.class)
                .collectList()
                .block();
    }

    public List<String> getCategory() {
        return webClient.get()
                .uri("https://fakestoreapi.com/products/categories")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<String>>(){})
                .block();
    }

}