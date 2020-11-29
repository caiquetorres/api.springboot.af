package com.app.clients.controllers;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import com.app.clients.dto.CreateClientDTO;
import com.app.clients.dto.GetClientDTO;
import com.app.clients.dto.UpdateClientDTO;
import com.app.clients.models.Client;
import com.app.clients.services.ClientService;
import com.app.common.GetManyDefaultResponse;
import com.app.reserve.dto.CreateReserveDTO;
import com.app.reserve.dto.GetReserveDTO;
import com.app.reserve.models.Reserve;
import com.app.reserve.services.ReserveService;
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
@RequestMapping(value = "clients")
public class ClientController {

    @Autowired
    public ClientService clientService;

    @Autowired
    public ReserveService reserveService;

    public ClientController() {
    }

    @PostMapping
    public ResponseEntity<GetClientDTO> createClient(
            @Valid @RequestBody CreateClientDTO createClientDTO, HttpServletRequest request,
            UriComponentsBuilder builder) {
        Client entity = this.clientService.save(createClientDTO);
        UriComponents uriComponents =
                builder.path(request.getRequestURI() + "/" + entity.toDto().getId()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PostMapping("/{idClient}/vehicles/{idVehicle}")
    public ResponseEntity<GetReserveDTO> createReserve(
            @Valid @RequestBody CreateReserveDTO createClientDTO, @PathVariable int idClient,
            @PathVariable int idVehicle, HttpServletRequest request, UriComponentsBuilder builder) {
        Reserve entity = this.reserveService.save(idClient, idVehicle, createClientDTO);
        UriComponents uriComponents =
                builder.path(request.getRequestURI() + "/" + entity.toDto().getId()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping("/{idClient}")
    public ResponseEntity<GetClientDTO> getClient(@PathVariable int idClient) {
        Client entity = this.clientService.findOne(idClient);
        return ResponseEntity.ok(entity.toDto());
    }

    @GetMapping
    public ResponseEntity<GetManyDefaultResponse<GetClientDTO>> getClients() {
        List<Client> entities = this.clientService.findAll(entity -> true);
        GetManyDefaultResponse<GetClientDTO> getManyDefaultResponse =
                new GetManyDefaultResponse<>();

        getManyDefaultResponse.setTotal(entities.size());
        getManyDefaultResponse.setElements(
                entities.stream().map(entity -> entity.toDto()).collect(Collectors.toList()));

        return ResponseEntity.ok(getManyDefaultResponse);
    }

    @GetMapping("/{idClient}/reserves")
    public ResponseEntity<GetManyDefaultResponse<GetReserveDTO>> getReserves(
            @PathVariable int idClient) {

        List<Reserve> entities = this.clientService.findReserves(idClient);
        GetManyDefaultResponse<GetReserveDTO> getManyDefaultResponse =
                new GetManyDefaultResponse<>();

        getManyDefaultResponse.setTotal(entities.size());
        getManyDefaultResponse.setElements(
                entities.stream().map(entity -> entity.toDto()).collect(Collectors.toList()));

        return ResponseEntity.ok(getManyDefaultResponse);
    }

    @PutMapping("/{idClient}")
    public ResponseEntity<Void> updateClient(@PathVariable int idClient,
            @Valid @RequestBody UpdateClientDTO updateClientDTO) {
        this.clientService.update(idClient, updateClientDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idClient}")
    public ResponseEntity<Void> updateClient(@PathVariable int idClient) {
        this.clientService.delete(idClient);
        return ResponseEntity.ok().build();
    }
}
