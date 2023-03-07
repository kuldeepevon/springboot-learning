package com.microservices.product.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<Object> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>("HttpMessageNotReadableException found", HttpStatus.BAD_REQUEST);
    }
}
