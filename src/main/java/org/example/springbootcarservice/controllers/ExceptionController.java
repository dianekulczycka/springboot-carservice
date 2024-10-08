package org.example.springbootcarservice.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public String throwException(Exception e) {
        return e.getLocalizedMessage();
    }
}
