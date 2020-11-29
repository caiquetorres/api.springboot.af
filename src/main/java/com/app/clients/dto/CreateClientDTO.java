package com.app.clients.dto;

import javax.validation.constraints.NotNull;
import com.app.clients.models.Client;
import com.app.common.ToEntity;
import org.hibernate.validator.constraints.br.CPF;

public class CreateClientDTO implements ToEntity<Client> {
    @NotNull(message = "It's required to send a name")
    private String name;

    @NotNull(message = "It's required to send a address")
    private String address;

    @NotNull(message = "It's required to send a valid cpf")
    @CPF(message = "It's required to send a valid cpf")
    private String cpf;

    // #region Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // #endregion

    @Override
    public Client toEntity() {
        Client entity = new Client();

        entity.setName(this.name);
        entity.setAddress(this.address);
        entity.setCpf(this.cpf);

        return entity;
    }
}
