package org.example.springbootcarservice.controllers;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.springbootcarservice.dto.CarDTO;
import org.example.springbootcarservice.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Data Access Object
// API (REST http) = a way to interact with our application

@RequestMapping("/cars")
@AllArgsConstructor

public class CarsController {

    private final CarService carService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<CarDTO> getCars() {
        return this.carService.getCars();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCar(@PathVariable Long id) {
        return new ResponseEntity<>(this.carService.getCarById(id), HttpStatus.OK);
    }

    @GetMapping("/minEnginePower")
    public ResponseEntity<CarDTO> getMinEnginePowerCar() {
        return new ResponseEntity<>(this.carService.getMinEnginePowerCar(), HttpStatus.OK);
    }

    @GetMapping("/maxEnginePower")
    public ResponseEntity<CarDTO> getMaxEnginePowerCar() {
        return new ResponseEntity<>(this.carService.getMaxEnginePowerCar(), HttpStatus.OK);
    }

    @RolesAllowed("SELLER")
    @PostMapping("")
    public ResponseEntity<List<CarDTO>> postCar(@RequestBody @Valid CarDTO carDTO) {
        this.carService.postCar(carDTO);
        return new ResponseEntity<>(this.carService.getCars(), HttpStatus.CREATED);
    }

    @RolesAllowed("SELLER")
    @DeleteMapping("/{id}")
    public ResponseEntity<List<CarDTO>> deleteCar(@PathVariable Long id) {
        this.carService.deleteCar(id);
        return new ResponseEntity<>(this.carService.getCars(), HttpStatus.OK);
    }

    @RolesAllowed("SELLER")
    @PutMapping("")
    public ResponseEntity<List<CarDTO>> putCar(@RequestBody CarDTO carDTO) {
        this.carService.updateCar(carDTO);
        return new ResponseEntity<>(this.carService.getCars(), HttpStatus.OK);
    }
}
