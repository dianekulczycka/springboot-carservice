package org.example.springbootcarservice.jobs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springbootcarservice.dto.CarDTO;
import org.example.springbootcarservice.service.CarService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor

public class sendAllCarsJob {
    public final CarService carService;

    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.SECONDS)
    public void sendAllCars() {
        List<CarDTO> cars = carService.getCars();
        if (cars != null) {
            log.info(cars.toString());
        } else {
            log.info("No cars");
        }
    }
}
