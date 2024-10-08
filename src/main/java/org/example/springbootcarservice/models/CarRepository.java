package org.example.springbootcarservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "cars")

@Repository
public class CarRepository {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "model")
    @NotBlank // jakarta validation
    private String model;

    @Column(nullable = false, name = "engine_power") // DB validation
    @Positive(message = "engine power must be bigger than 0")
    private int enginePower;

    public CarRepository(String model, int enginePower) {
        this.model = model;
        this.enginePower = enginePower;
    }
}
