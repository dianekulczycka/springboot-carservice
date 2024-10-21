package org.example.springbootcarservice.entities;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("maintenance")
@Data
public class Maintenance {
    @MongoId
    private ObjectId id;
    private String name;
    private String description;
    private Double price;
}
