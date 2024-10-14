package org.example.springbootcarservice.mapper;

import org.example.springbootcarservice.entities.Car;
import org.example.springbootcarservice.dto.CarDTO;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    public CarDTO mapToDTO(Car car) {
        CarDTO dto = new CarDTO();
        dto.setId(car.getId());
        dto.setModel(car.getModel());
        return dto;
    }

    public Car mapToEntity(CarDTO dto) {
        Car car = new Car();
        car.setId(dto.getId());
        car.setModel(dto.getModel());
        return car;
    }
}
