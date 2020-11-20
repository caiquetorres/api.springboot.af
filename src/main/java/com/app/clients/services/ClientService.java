package com.app.clients.services;

import java.util.List;
import java.util.function.Predicate;

import com.app.clients.dto.CreateClientDTO;
import com.app.clients.dto.UpdateClientDTO;
import com.app.clients.models.Client;
import com.app.clients.repositories.ClientRepository;
import com.app.exceptions.DefaultExceptionMessages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClientService {

    @Autowired
    public ClientRepository repository;

    public ClientService() {
    }

    public Client save(CreateClientDTO dto) {
        Client entity = dto.toEntity();
        this.repository.save(entity);
        return entity;
    }

    public Client findOne(int id) {
        Client entity = this.repository.findOne(id);
        if (entity == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    DefaultExceptionMessages.entityNotFound(Integer.toString(id)));
        return entity;
    }

    public List<Client> findAll(Predicate<Client> predicate) {
        return this.repository.findAll(predicate);
    }

    public void delete(int id) {
        if (!this.repository.contains(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    DefaultExceptionMessages.entityNotFound(Integer.toString(id)));

        this.repository.delete(id);
    }

    public void update(int id, UpdateClientDTO updateClientDTO) {
        if (!this.repository.contains(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    DefaultExceptionMessages.entityNotFound(Integer.toString(id)));

        this.repository.update(id, updateClientDTO);
    }
}
