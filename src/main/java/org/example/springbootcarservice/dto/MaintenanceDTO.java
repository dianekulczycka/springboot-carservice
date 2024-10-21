package org.example.springbootcarservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor

public class MaintenanceDTO {
    private ObjectId id;
    private String name;
    private String description;
    private Double price;
}
