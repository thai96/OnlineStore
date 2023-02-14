package com.example.mynote.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequestException(
            BadRequestException ex, WebRequest request
    ){
        String message = ex.getApiResponse().getMessage();
        HttpStatus status = ex.getApiResponse().getStatus() != null ? ex.getApiResponse().getStatus():HttpStatus.BAD_REQUEST;
        return handleExceptionInternal(ex, message, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request
    ){
        String message = ex.getMessage();
        HttpStatus status = ex.getStatus() != null ? ex.getStatus():HttpStatus.NOT_FOUND;
        return handleExceptionInternal(ex, message, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(value = ShopApiException.class)
    protected ResponseEntity<Object> handleShopApiException(
            ShopApiException ex, WebRequest request
    ){
        String message = ex.getMessage();
        HttpStatus status = ex.getStatus();
        return handleExceptionInternal(ex, message, new HttpHeaders(), status, request);
    }
}
