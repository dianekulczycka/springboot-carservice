package org.example.springbootcarservice.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.example.springbootcarservice.dto.MaintenanceDTO;
import org.example.springbootcarservice.mapper.MaintenanceMapper;
import org.example.springbootcarservice.repository.MaintenanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;
    private final MaintenanceMapper maintenanceMapper;

    public List<MaintenanceDTO> getMaintenances() {
        return maintenanceRepository
                .findAll()
                .stream()
                .map(maintenanceMapper::mapToDTO)
                .toList();
    }

    public MaintenanceDTO getMaintenanceById(ObjectId id) {
        return maintenanceMapper.mapToDTO(maintenanceRepository.findById(id).get());
    }

    public void postMaintenance(MaintenanceDTO maintenanceDTO) {
        maintenanceRepository.save(maintenanceMapper.mapToEntity(maintenanceDTO));
    }

    public void deleteMaintenance(ObjectId id) {
        maintenanceRepository.deleteById(id);
    }

    public void updateMaintenance(MaintenanceDTO maintenanceDTO) {
        maintenanceRepository.save(maintenanceMapper.mapToEntity(maintenanceDTO));
    }
}
