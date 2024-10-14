package org.example.springbootcarservice.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootcarservice.dto.CarDTO;
import org.example.springbootcarservice.entities.Car;
import org.example.springbootcarservice.mapper.CarMapper;
import org.example.springbootcarservice.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public List<CarDTO> getCars() {
        return carRepository
                .findAll()
                .stream()
                .map(carMapper::mapToDTO)
                .toList();
    }

    public CarDTO getCarById(Long id) {
        return this.carMapper.mapToDTO(carRepository.findById(id).get());
    }

    public CarDTO getMinEnginePowerCar() {
        return this.carMapper.mapToDTO(this.carRepository.findMinPower());
    }

    public CarDTO getMaxEnginePowerCar() {
        return this.carMapper.mapToDTO(this.carRepository.findMaxPower());
    }

    public void postCar(String model, int enginePower) {
        this.carRepository.save(new Car(model, enginePower));
    }

    public void deleteCar(Long id) {
        Optional<Car> carToDelete = this.carRepository.findById(id);
        this.carRepository.delete(carToDelete.get());
    }

    public void updateCar(Car car) {
        this.carRepository.save(car);
    }


}
