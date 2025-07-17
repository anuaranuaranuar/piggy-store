package com.anuar.piggy_store.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.anuar.piggy_store.dto.response.ApiResponse;

@RestControllerAdvice
public class GoblalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> hadleIllegalArgument(IllegalArgumentException ex){
        ApiResponse<Object> response = new ApiResponse<>(
            false,
            "Error interno",
            "INTERNAL_ERROR",
            null,
            List.of(ex.getMessage())
        );

        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
