package com.app.reserve.models;

import java.util.Date;

import com.app.clients.models.Client;
import com.app.common.BaseEntity;
import com.app.common.ToDTO;
import com.app.reserve.dto.GetReserveDTO;
import com.app.vehicles.models.Vehicle;

public class Reserve extends BaseEntity implements ToDTO<GetReserveDTO> {
    private Client client;
    private Vehicle vehicle;
    private Date from;
    private Date to;
    private float totalCost;

    // #region Getters and Setters

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    // #endregion

    @Override
    public GetReserveDTO toDto() {
        GetReserveDTO dto = new GetReserveDTO();

        dto.setId(this.id);
        dto.setFrom(this.from);
        dto.setTo(this.to);
        dto.setClient(this.client.toDto());
        dto.setVehicle(this.vehicle.toDto());

        return dto;
    }
}
