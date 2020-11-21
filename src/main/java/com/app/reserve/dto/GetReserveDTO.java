package com.app.reserve.dto;

import java.util.Date;

import com.app.clients.dto.GetClientDTO;
import com.app.vehicles.dto.GetVehicleDTO;

public class GetReserveDTO {
    private int id;
    private Date from;
    private Date to;
    private float totalCost;
    private GetClientDTO client;
    private GetVehicleDTO vehicle;

    // #region Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GetClientDTO getClient() {
        return client;
    }

    public void setClient(GetClientDTO client) {
        this.client = client;
    }

    public GetVehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(GetVehicleDTO vehicle) {
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
}
