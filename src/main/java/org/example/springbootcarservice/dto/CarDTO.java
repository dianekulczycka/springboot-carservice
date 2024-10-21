package org.example.springbootcarservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

// POJO plain old java obj
public class CarDTO {
    @NotNull
    private long id;
    @NotBlank
    private String model;
    @NotNull
    private Integer enginePower;
}
