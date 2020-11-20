package com.app.vehicles.repositories;

import com.app.common.BaseRepository;
import com.app.vehicles.dto.CreateVehicleDTO;
import com.app.vehicles.dto.UpdateVehicleDTO;
import com.app.vehicles.models.Vehicle;

import org.springframework.stereotype.Component;

@Component
public class VehicleRepository extends BaseRepository<Vehicle, CreateVehicleDTO, UpdateVehicleDTO> {
}
