package com.app.reserve.controllers;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.app.common.GetManyDefaultResponse;
import com.app.reserve.dto.CreateReserveDTO;
import com.app.reserve.dto.GetReserveDTO;
import com.app.reserve.models.Reserve;
import com.app.reserve.services.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping
public class ReserveController {

    @Autowired
    public ReserveService reserveService;

    public ReserveController() {
    }

    @PostMapping("/clients/{idClient}/vehicles/{idVehicle}")
    public ResponseEntity<GetReserveDTO> createReserve(
            @RequestBody CreateReserveDTO createClientDTO, @PathVariable int idClient,
            @PathVariable int idVehicle, HttpServletRequest request, UriComponentsBuilder builder) {
        Reserve entity = this.reserveService.save(idClient, idVehicle, createClientDTO);
        UriComponents uriComponents =
                builder.path(request.getRequestURI() + "/" + entity.toDto().getId()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping("/reserves/{idReserve}")
    public ResponseEntity<GetReserveDTO> getReserve(@PathVariable int idReserve) {
        System.out.println(idReserve);
        Reserve entity = this.reserveService.findOne(idReserve);
        return ResponseEntity.ok(entity.toDto());
    }

    @GetMapping("/reserves")
    public ResponseEntity<GetManyDefaultResponse<GetReserveDTO>> getReserves() {
        List<Reserve> entities = this.reserveService.findAll(entity -> true);
        GetManyDefaultResponse<GetReserveDTO> getManyDefaultResponse =
                new GetManyDefaultResponse<>();

        getManyDefaultResponse.setTotal(entities.size());
        getManyDefaultResponse.setElements(
                entities.stream().map(entity -> entity.toDto()).collect(Collectors.toList()));

        return ResponseEntity.ok(getManyDefaultResponse);
    }
}
