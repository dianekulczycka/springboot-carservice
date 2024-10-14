package org.example.springbootcarservice.controllers;

import org.example.springbootcarservice.dto.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorDTO> throwException(Exception e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(e.getMessage());
        errorDTO.setTimestamp(LocalDateTime.now());
        return ResponseEntity.badRequest().body(errorDTO);
    }
}
