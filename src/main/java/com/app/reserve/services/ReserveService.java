package com.app.reserve.services;

import java.util.List;
import java.util.function.Predicate;
import com.app.clients.models.Client;
import com.app.clients.services.ClientService;
import com.app.exceptions.DefaultExceptionMessages;
import com.app.reserve.dto.CreateReserveDTO;
import com.app.reserve.dto.UpdateReserveDTO;
import com.app.reserve.models.Reserve;
import com.app.reserve.repositories.ReserveRepository;
import com.app.vehicles.models.Vehicle;
import com.app.vehicles.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReserveService {
    @Autowired
    public ReserveRepository repository;

    @Autowired
    public ClientService clientService;

    @Autowired
    public VehicleService vehicleService;

    public ReserveService() {
    }

    public Reserve save(int idClient, int idVehicle, CreateReserveDTO dto) {
        Reserve entity = dto.toEntity();

        // If no client or no vehicle those services will throw an exception
        Client clientEntity = this.clientService.findOne(idClient);
        Vehicle vehicleEntity = this.vehicleService.findOne(idVehicle);

        entity.setClient(clientEntity);
        entity.setVehicle(vehicleEntity);

        this.repository.save(entity);
        return entity;
    }

    public Reserve findOne(int id) {
        Reserve entity = this.repository.findOne(id);
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    DefaultExceptionMessages.entityNotFound(Integer.toString(id)));
        return entity;
    }

    public List<Reserve> findAll(Predicate<Reserve> predicate) {
        return this.repository.findAll(predicate);
    }

    public void delete(int id) {
        if (!this.repository.contains(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    DefaultExceptionMessages.entityNotFound(Integer.toString(id)));

        this.repository.delete(id);
    }

    public void update(int id, UpdateReserveDTO updateReserveDTO) {
        if (!this.repository.contains(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    DefaultExceptionMessages.entityNotFound(Integer.toString(id)));

        this.repository.update(id, updateReserveDTO);
    }
}
