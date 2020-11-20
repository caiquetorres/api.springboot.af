package com.app.reserve.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "clients/{idClient}/vehicles/{idVehicle}")
public class ReserveController {
    public ReserveController() {
    }
}
