package com.app.reserve.dto;

import java.util.Date;

import com.app.common.ToEntity;
import com.app.reserve.models.Reserve;

public class CreateReserveDTO implements ToEntity<Reserve> {
    private int clientId;
    private int vehicleID;
    private Date from;
    private Date to;

    // #region Getters and Setters

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
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

    // #endregion

    @Override
    public Reserve toEntity() {
        Reserve entity = new Reserve();

        entity.setFrom(this.from);
        entity.setTo(this.to);

        return entity;
    }
}
