package org.example.springbootcarservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data

public class ErrorDTO {
    private String message;
    private LocalDateTime timestamp;
}
