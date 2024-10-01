package org.example.springbootcarservice.controllers;

import lombok.RequiredArgsConstructor;
import org.example.springbootcarservice.data.FuelType;
import org.example.springbootcarservice.data.ReferenceData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class ReferenceDataController {

    private final ReferenceData referenceData;

    @Value("${reference-data.engine-types}")
    private List<String> engineTypes;

    @GetMapping("/engine-types")
    public List<String> getEngineTypes() {
        return engineTypes;
    }

    @GetMapping("/fuel-types")
    public List<FuelType> getFuelTypes() {
        return referenceData.getFuelTypes();
    }

    @GetMapping("/fuel-types/{type}")
    public FuelType getFuelType(@PathVariable String type) {
        for (FuelType fuelType : referenceData.getFuelTypes()) {
            if (fuelType.getType().equals(type)) {
                return fuelType;
            }
        }
        return null;
    }
}
