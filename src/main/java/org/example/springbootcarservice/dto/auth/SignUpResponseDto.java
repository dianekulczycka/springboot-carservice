package org.example.springbootcarservice.dto.auth;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder

public class SignUpResponseDto {
    private Integer id;
    private String email;
    private LocalDateTime registrationDate;
}
