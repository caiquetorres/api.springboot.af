package com.app.reserve.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import javax.xml.crypto.Data;
import com.app.clients.models.Client;
import com.app.clients.services.ClientService;
import com.app.exceptions.DefaultExceptionMessages;
import com.app.reserve.dto.CreateReserveDTO;
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

        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

        if (dto.getFrom().before(now))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The final date cannot be before the current date");

        if (dto.getTo().before(dto.getFrom()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The final date cannot be before the start date");

        List<Reserve> vehicleReserves = this.vehicleService.findReserves(idVehicle);
        boolean alreadyReserved = vehicleReserves.stream()
                .anyMatch(reserve -> isReserved(reserve, dto.getFrom(), dto.getTo()));
        if (alreadyReserved)
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "This vehicle has already a reserve in those dates");

        calendar.setTime(dto.getFrom());
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
        if (calendar.get(Calendar.DAY_OF_WEEK) == 7)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A vehicle cannot be rented on sunday");

        calendar.setTime(dto.getTo());
        if (calendar.get(Calendar.DAY_OF_WEEK) == 7)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A vehicle cannot be delivered on sunday");

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

    // #region Utils

    private boolean isReserved(Reserve reserve, Date from, Date to) {
        Date reserveFrom = reserve.getFrom();
        Date reserveTo = reserve.getTo();
        return from.after(reserveFrom) && from.before(reserveTo)
                || to.after(reserveFrom) && to.before(reserveTo) || to.equals(reserveTo)
                || to.equals(reserveFrom) || from.equals(reserveTo) || from.equals(reserveFrom);
    }

    // #endregion
}
