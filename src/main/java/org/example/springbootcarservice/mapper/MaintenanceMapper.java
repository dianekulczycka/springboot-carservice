package org.example.springbootcarservice.mapper;

import org.example.springbootcarservice.dto.MaintenanceDTO;
import org.example.springbootcarservice.entities.Maintenance;
import org.springframework.stereotype.Component;

@Component
public class MaintenanceMapper {

    public MaintenanceDTO mapToDTO(Maintenance maintenance) {
        MaintenanceDTO dto = new MaintenanceDTO();
        dto.setId(maintenance.getId());
        dto.setName(maintenance.getName());
        dto.setDescription(maintenance.getDescription());
        dto.setPrice(maintenance.getPrice());
        return dto;
    }

    public Maintenance mapToEntity(MaintenanceDTO dto) {
        Maintenance maintenance = new Maintenance();
        maintenance.setId(dto.getId());
        maintenance.setName(dto.getName());
        maintenance.setDescription(dto.getDescription());
        maintenance.setPrice(dto.getPrice());
        return maintenance;
    }
}
