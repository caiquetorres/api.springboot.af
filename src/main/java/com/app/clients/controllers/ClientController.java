package com.app.clients.controllers;

import com.app.clients.services.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "clients")
public class ClientController {

    @Autowired
    public ClientService clientService;

    public ClientController() {
    }
}
