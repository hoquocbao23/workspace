package fpt.swp.workspace.service;

import fpt.swp.workspace.models.Building;
import fpt.swp.workspace.repository.BuildingRepository;
import fpt.swp.workspace.response.BuildingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    public Building createBuilding(BuildingRequest request) {
        Building building = new Building();
        building.setBuildingId(UUID.randomUUID().toString());
        building.setBuildingName(request.getBuildingName());
        building.setBuildingLocation(request.getBuildingLocation());
        building.setPhoneContact(request.getPhoneContact());

        return buildingRepository.save(building);
    }

    public List<Building> findAllBuildings() {
        return buildingRepository.findAll();
    }

    public Building findBuildingById(String wsId) {
        return buildingRepository.findById(wsId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy building"));
    }

    public Building updateBuilding(String wsId, BuildingRequest request) {
        Building building = buildingRepository.findById(wsId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy building"));

        if (request.getBuildingName() != null) {
            building.setBuildingName(request.getBuildingName());
        }
        if (request.getBuildingLocation() != null) {
            building.setBuildingLocation(request.getBuildingLocation());
        }
        if (request.getPhoneContact() != null) {
            building.setPhoneContact(request.getPhoneContact());
        }
        return buildingRepository.save(building);
    }

    public void deleteBuilding(String wsId) {
        Building building = buildingRepository.findById(wsId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy building"));

        buildingRepository.delete(building);
    }
}
