package org.example.springbootcarservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor

// POJO plain old java obj
public class CarDTO implements Serializable {
    @NotNull
    private long id;
    @NotBlank
    @Min(1)
    private String model;
}
