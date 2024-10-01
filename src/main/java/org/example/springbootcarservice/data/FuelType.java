package org.example.springbootcarservice.data;

import lombok.Data;

import java.util.List;

@Data

public class FuelType {
    private String type;
    private List<String> varieties;
}
