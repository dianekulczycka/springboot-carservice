package org.example.springbootcarservice.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "reference-data")

public class ReferenceData {
    private List<FuelType> fuelTypes;
}
