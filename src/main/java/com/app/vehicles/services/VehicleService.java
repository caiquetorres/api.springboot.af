package com.app.vehicles.services;

import java.util.List;
import java.util.function.Predicate;

import com.app.exceptions.DefaultExceptionMessages;
import com.app.reserve.models.Reserve;
import com.app.reserve.services.ReserveService;
import com.app.vehicles.dto.CreateVehicleDTO;
import com.app.vehicles.dto.UpdateVehicleDTO;
import com.app.vehicles.models.Vehicle;
import com.app.vehicles.repositories.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VehicleService {
    @Autowired
    public VehicleRepository repository;

    @Autowired
    public ReserveService reserveService;

    public VehicleService() {
    }

    public Vehicle save(CreateVehicleDTO dto) {
        Vehicle entity = dto.toEntity();
        this.repository.save(entity);
        return entity;
    }

    public Vehicle findOne(int id) {
        Vehicle entity = this.repository.findOne(id);
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    DefaultExceptionMessages.entityNotFound(Integer.toString(id)));
        return entity;
    }

    public List<Vehicle> findAll(Predicate<Vehicle> predicate) {
        return this.repository.findAll(predicate);
    }

    public List<Reserve> findReserves(int id) {
        if (!this.repository.contains(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    DefaultExceptionMessages.entityNotFound(Integer.toString(id)));

        return this.reserveService
                .findAll(reserve -> reserve.getVehicle().getId() == findOne(id).getId());
    }

    public void delete(int id) {
        if (!this.repository.contains(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    DefaultExceptionMessages.entityNotFound(Integer.toString(id)));

        this.repository.delete(id);
    }

    public void update(int id, UpdateVehicleDTO updateVehicleDTO) {
        if (!this.repository.contains(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    DefaultExceptionMessages.entityNotFound(Integer.toString(id)));

        this.repository.update(id, updateVehicleDTO);
    }
}
