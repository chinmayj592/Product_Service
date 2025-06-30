package com.example.productservice.controllerAdvices;

import com.example.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException exception){
        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST
        );

    }
//    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
//    public void handleArrayIndexOutOfBoundException(){
//
//    }
//    @ExceptionHandler(Exception.class)
//    public void handleException(){
//
//    }
}
