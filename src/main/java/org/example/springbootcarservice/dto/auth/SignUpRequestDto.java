package org.example.springbootcarservice.dto.auth;

import lombok.Builder;
import lombok.Data;
import org.example.springbootcarservice.entities.Customer;

@Data
@Builder
public class SignUpRequestDto {
    private String email;
    private String password;
    private Customer.Roles role;
}
