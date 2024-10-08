package org.example.springbootcarservice.models;

import jakarta.persistence.*;
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

    @Column(nullable = false, name = "model")
    private String model;

    @Column(nullable = false, name = "engine_power")
    private int enginePower;

    public CarRepository(String model, int enginePower) {
        this.model = model;
        this.enginePower = enginePower;
    }
}
