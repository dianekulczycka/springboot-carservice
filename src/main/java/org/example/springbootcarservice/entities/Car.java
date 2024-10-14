package org.example.springbootcarservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@NoArgsConstructor
@Data

@Entity
@Table(name = "cars")

@Repository
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "model")
    @NotBlank // jakarta validation
    private String model;

    @Column(nullable = false, name = "engine_power") // DB validation
    @Positive(message = "engine power must be bigger than 0")
    private int enginePower;

    public Car(String model, int enginePower) {
        this.model = model;
        this.enginePower = enginePower;
    }
}
