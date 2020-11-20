package com.app.clients.models;

import com.app.clients.dto.GetClientDTO;
import com.app.common.BaseEntity;
import com.app.common.ToDTO;

public class Client extends BaseEntity implements ToDTO<GetClientDTO> {
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
    public GetClientDTO toDto() {
        GetClientDTO dto = new GetClientDTO();

        dto.setId(this.id);
        dto.setName(this.name);
        dto.setAddress(this.address);
        dto.setCpf(this.cpf);

        return dto;
    }
}
