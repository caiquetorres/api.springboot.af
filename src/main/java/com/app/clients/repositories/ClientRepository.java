package com.app.clients.repositories;

import com.app.clients.dto.CreateClientDTO;
import com.app.clients.dto.UpdateClientDTO;
import com.app.clients.models.Client;
import com.app.common.BaseRepository;

import org.springframework.stereotype.Component;

@Component
public class ClientRepository extends BaseRepository<Client, CreateClientDTO, UpdateClientDTO> {
}
