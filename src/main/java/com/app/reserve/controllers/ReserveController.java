package com.app.reserve.controllers;

import java.util.List;
import java.util.stream.Collectors;
import com.app.common.GetManyDefaultResponse;
import com.app.reserve.dto.GetReserveDTO;
import com.app.reserve.dto.UpdateReserveDTO;
import com.app.reserve.models.Reserve;
import com.app.reserve.services.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "reserves")
public class ReserveController {

    @Autowired
    public ReserveService reserveService;

    public ReserveController() {
    }

    @GetMapping("/{idReserve}")
    public ResponseEntity<GetReserveDTO> getReserve(@PathVariable int idReserve) {
        Reserve entity = this.reserveService.findOne(idReserve);
        return ResponseEntity.ok(entity.toDto());
    }

    @GetMapping
    public ResponseEntity<GetManyDefaultResponse<GetReserveDTO>> getReserves() {
        List<Reserve> entities = this.reserveService.findAll(entity -> true);
        GetManyDefaultResponse<GetReserveDTO> getManyDefaultResponse =
                new GetManyDefaultResponse<>();

        getManyDefaultResponse.setTotal(entities.size());
        getManyDefaultResponse.setElements(
                entities.stream().map(entity -> entity.toDto()).collect(Collectors.toList()));

        return ResponseEntity.ok(getManyDefaultResponse);
    }

    @DeleteMapping("/{idReserve}")
    public ResponseEntity<Void> updateReserve(@PathVariable int idReserve) {

        this.reserveService.delete(idReserve);
        return ResponseEntity.ok().build();
    }
}
