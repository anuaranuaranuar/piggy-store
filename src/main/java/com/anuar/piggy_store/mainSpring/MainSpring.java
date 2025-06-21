package com.anuar.piggy_store.mainSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.anuar.piggy_store.external.api.FakeStoreApi;

@Component
public class MainSpring implements CommandLineRunner {

    private final FakeStoreApi fakeStoreApi;


    public MainSpring(FakeStoreApi fakeStoreApi){
        this.fakeStoreApi=fakeStoreApi;
    }

    @Override
    public void run(String... args) throws Exception {

       System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");//System.out.println(fakeStoreApi.getProducts());
       System.out.println(fakeStoreApi.getCategory());
       System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    }
    
}
