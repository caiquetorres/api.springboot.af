package com.app.clients.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.app.clients.models.Client;
import com.app.common.ModifyEntity;
import org.hibernate.validator.constraints.br.CPF;

public class UpdateClientDTO implements ModifyEntity<Client> {
    @NotBlank(message = "It's required to send a name")
    @NotNull(message = "It's required to send a name")
    private String name;

    @NotBlank(message = "It's required to send a address")
    @NotNull(message = "It's required to send a address")
    private String address;

    @NotBlank(message = "It's required to send a valid cpf")
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
    public void modifyEntity(Client entity) {
        entity.setName(this.name);
        entity.setAddress(this.address);
        entity.setCpf(this.cpf);
    }
}
