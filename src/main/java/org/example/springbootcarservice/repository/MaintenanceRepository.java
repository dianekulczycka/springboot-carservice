package org.example.springbootcarservice.repository;

import org.bson.types.ObjectId;
import org.example.springbootcarservice.entities.Maintenance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends MongoRepository<Maintenance, ObjectId> {
}
