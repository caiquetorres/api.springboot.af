package com.app.vehicles.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.app.common.GetManyDefaultResponse;
import com.app.reserve.dto.GetReserveDTO;
import com.app.reserve.models.Reserve;
import com.app.vehicles.dto.CreateVehicleDTO;
import com.app.vehicles.dto.GetVehicleDTO;
import com.app.vehicles.dto.UpdateVehicleDTO;
import com.app.vehicles.models.Vehicle;
import com.app.vehicles.services.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "vehicles")
public class VehicleController {

    @Autowired
    public VehicleService vehicleService;

    public VehicleController() {
    }

    @PostMapping
    public ResponseEntity<GetVehicleDTO> createVehicle(
            @RequestBody final CreateVehicleDTO createVehicleDTO, final HttpServletRequest request,
            final UriComponentsBuilder builder) {
        Vehicle entity = this.vehicleService.save(createVehicleDTO);
        UriComponents uriComponents =
                builder.path(request.getRequestURI() + "/" + entity.toDto().getId()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping("/{idVehicle}")
    public ResponseEntity<GetVehicleDTO> getVehicle(@PathVariable int idVehicle) {
        Vehicle entity = this.vehicleService.findOne(idVehicle);
        return ResponseEntity.ok(entity.toDto());
    }

    @GetMapping
    public ResponseEntity<GetManyDefaultResponse<GetVehicleDTO>> getVehicles() {
        List<Vehicle> entities = this.vehicleService.findAll(entity -> true);
        GetManyDefaultResponse<GetVehicleDTO> getManyDefaultResponse =
                new GetManyDefaultResponse<>();

        getManyDefaultResponse.setTotal(entities.size());
        getManyDefaultResponse.setElements(
                entities.stream().map(entity -> entity.toDto()).collect(Collectors.toList()));

        return ResponseEntity.ok(getManyDefaultResponse);
    }

    @GetMapping("/{idVehicle}/reserves")
    public ResponseEntity<GetManyDefaultResponse<GetReserveDTO>> getReserves(
            @PathVariable int idVehicle) {
        List<Reserve> entities = this.vehicleService.findReserves(idVehicle);
        GetManyDefaultResponse<GetReserveDTO> getManyDefaultResponse =
                new GetManyDefaultResponse<>();

        getManyDefaultResponse.setTotal(entities.size());
        getManyDefaultResponse.setElements(
                entities.stream().map(entity -> entity.toDto()).collect(Collectors.toList()));

        return ResponseEntity.ok(getManyDefaultResponse);
    }

    @PutMapping("/{idVehicle}")
    public ResponseEntity<Void> updateVehicle(@PathVariable int idVehicle,
            @RequestBody UpdateVehicleDTO updateVehicleDTO) {
        this.vehicleService.update(idVehicle, updateVehicleDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idVehicle}")
    public ResponseEntity<Void> updateVehicle(@PathVariable int idVehicle) {
        this.vehicleService.delete(idVehicle);
        return ResponseEntity.ok().build();
    }
}
