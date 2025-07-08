package com.anuar.piggy_store.dto.response;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    private Boolean success;
    private String message;
    private String code;
    private T data;
    private List<String> errors;
    private Instant timestamp;

    public ApiResponse(Boolean success, String message, String code, T data, List<String> errors) {
        this.success = success;
        this.message = message;
        this.code = code;
        this.data = data;
        this.errors = errors;
        this.timestamp = Instant.now();
    }

	public ApiResponse() {
        this.timestamp=Instant.now();
	}

    

}
