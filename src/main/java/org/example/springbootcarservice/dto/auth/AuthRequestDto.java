package org.example.springbootcarservice.dto.auth;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.example.springbootcarservice.entities.Customer;

@Data
@Builder
public class AuthRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    @Min(2)
    private String password;
    @NotBlank
    private Customer.Roles role;
}
