package com.example.mynote.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException{
    public final HttpStatus status;
    public final String message;
    public ResourceNotFoundException(String message){
        super();
        this.status = HttpStatus.NOT_FOUND;
        this.message = message;
    }
    public ResourceNotFoundException(HttpStatus status, String message){
        super();
        this.status = status;
        this.message = message;
    }
    public ResourceNotFoundException(HttpStatus status, String message, Throwable exception){
        super(exception);
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
