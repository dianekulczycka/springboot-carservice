package org.example.springbootcarservice.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.springbootcarservice.dao.CarsDAO;
import org.example.springbootcarservice.models.CarRepository;
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
    public List<CarRepository> getAllCars() {
        return this.carsDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarRepository> getCar(@PathVariable int id) {
        return new ResponseEntity<>(this.carsDAO.findById(id), HttpStatus.OK);
    }

    @GetMapping("/minEnginePower")
    public ResponseEntity<CarRepository> getMinEnginePowerCar() {
        return new ResponseEntity<>(this.carsDAO.findMinPower(), HttpStatus.OK);
    }

    @GetMapping("/maxEnginePower")
    public ResponseEntity<CarRepository> getMaxEnginePowerCar() {
        return new ResponseEntity<>(this.carsDAO.findMaxPower(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<List<CarRepository>> postCar(@RequestParam @Valid String model,
                                                       @RequestParam @Valid int enginePower) {
        this.carsDAO.save(new CarRepository(model, enginePower));
        return new ResponseEntity<>(this.carsDAO.findAll(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<CarRepository>> deleteCar(@PathVariable int id) {
        this.carsDAO.delete(id);
        return new ResponseEntity<>(this.carsDAO.findAll(), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<List<CarRepository>> putCar(@RequestBody CarRepository customer) {
        this.carsDAO.update(customer);
        return new ResponseEntity<>(this.carsDAO.findAll(), HttpStatus.OK);
    }
}
