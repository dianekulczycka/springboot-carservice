package org.example.springbootcarservice.controllers;

import lombok.AllArgsConstructor;
import org.example.springbootcarservice.dao.CarsDAO;
import org.example.springbootcarservice.models.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@AllArgsConstructor

public class CarsController {

    private CarsDAO carsDAO;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> getAllCars() {
        return this.carsDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable int id) {
        return new ResponseEntity<>(this.carsDAO.findById(id), HttpStatus.OK);
    }

    @GetMapping("/minEnginePower")
    public ResponseEntity<Car> getMinEnginePowerCar() {
        return new ResponseEntity<>(this.carsDAO.findMinPower(), HttpStatus.OK);
    }

    @GetMapping("/maxEnginePower")
    public ResponseEntity<Car> getMaxEnginePowerCar() {
        return new ResponseEntity<>(this.carsDAO.findMaxPower(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<List<Car>> postCar(@RequestParam String model,
                                             @RequestParam int enginePower) {
        this.carsDAO.save(new Car(model, enginePower));
        return new ResponseEntity<>(this.carsDAO.findAll(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Car>> deleteCar(@PathVariable int id) {
        this.carsDAO.delete(id);
        return new ResponseEntity<>(this.carsDAO.findAll(), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<List<Car>> putCar(@RequestBody Car customer) {
        this.carsDAO.update(customer);
        return new ResponseEntity<>(this.carsDAO.findAll(), HttpStatus.OK);
    }
}
