package com.app.clients.dto;

import com.app.clients.models.Client;
import com.app.common.ModifyEntity;

public class UpdateClientDTO implements ModifyEntity<Client> {
    private String name;
    private String address;
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
    public void modifyEntity(Client entity) {
        entity.setName(this.name);
        entity.setAddress(this.address);
        entity.setCpf(this.cpf);
    }
}
