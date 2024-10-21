package org.example.springbootcarservice.controllers;

import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.example.springbootcarservice.dto.MaintenanceDTO;
import org.example.springbootcarservice.service.MaintenanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
@AllArgsConstructor

public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @GetMapping("")
    public ResponseEntity<List<MaintenanceDTO>> getMaintenances() {
        return new ResponseEntity<>(this.maintenanceService.getMaintenances(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceDTO> getMaintenance(@PathVariable ObjectId id) {
        return new ResponseEntity<>(this.maintenanceService.getMaintenanceById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<List<MaintenanceDTO>> addMaintenance(@RequestBody MaintenanceDTO maintenanceDTO) {
        this.maintenanceService.postMaintenance(maintenanceDTO);
        return new ResponseEntity<>(this.maintenanceService.getMaintenances(), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<List<MaintenanceDTO>> updateMaintenance(@RequestBody MaintenanceDTO maintenanceDTO) {
        this.maintenanceService.updateMaintenance(maintenanceDTO);
        return new ResponseEntity<>(this.maintenanceService.getMaintenances(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<MaintenanceDTO>> deleteMaintenance(@PathVariable String id) {
        ObjectId objectId = new ObjectId(id);
        this.maintenanceService.deleteMaintenance(objectId);
        return new ResponseEntity<>(this.maintenanceService.getMaintenances(), HttpStatus.OK);
    }
}
