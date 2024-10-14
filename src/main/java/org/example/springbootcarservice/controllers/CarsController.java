package org.example.springbootcarservice.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.springbootcarservice.entities.Car;
import org.example.springbootcarservice.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
@AllArgsConstructor

public class CarsController {

    private final Car car;
    private CarRepository carRepository;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> getAllCars() {
        return this.carRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable long id) {
        if (this.carRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(this.carRepository.findById(id).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/minEnginePower")
    public ResponseEntity<Car> getMinEnginePowerCar() {
        return new ResponseEntity<>(this.carRepository.findMinPower(), HttpStatus.OK);
    }

    @GetMapping("/maxEnginePower")
    public ResponseEntity<Car> getMaxEnginePowerCar() {
        return new ResponseEntity<>(this.carRepository.findMaxPower(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<List<Car>> postCar(@RequestParam @Valid String model,
                                             @RequestParam @Valid int enginePower) {
        this.carRepository.save(new Car(model, enginePower));
        return new ResponseEntity<>(this.carRepository.findAll(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Car>> deleteCar(@PathVariable long id) {
        Optional<Car> carToDelete = this.carRepository.findById(id);
        if (carToDelete.isPresent()) {
            this.carRepository.delete(carToDelete.get());
            return new ResponseEntity<>(this.carRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("")
    public ResponseEntity<List<Car>> putCar(@RequestBody Car car) {
        if (this.carRepository.findById(car.getId()).isPresent()) {
            this.carRepository.save(car);
            return new ResponseEntity<>(this.carRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
