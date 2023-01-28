package com.example.mynote.exception;

import com.example.mynote.payload.ApiResponse;

public class BadRequestException extends RuntimeException{
    private ApiResponse apiResponse;

    public BadRequestException(ApiResponse apiResponse) {
        super();
        this.apiResponse = apiResponse;
    }

    public BadRequestException(String message, ApiResponse apiResponse) {
        super(message);
        this.apiResponse = apiResponse;
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiResponse getApiResponse() {
        return apiResponse;
    }
}
