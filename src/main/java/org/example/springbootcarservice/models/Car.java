package org.example.springbootcarservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "cars")

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "model")
    private String model;

    @Column(nullable = false, name = "engine_power")
    private int enginePower;

    public Car(String model, int enginePower) {
        this.model = model;
        this.enginePower = enginePower;
    }
}
