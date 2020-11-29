package com.app.vehicles.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.app.common.ToEntity;
import com.app.vehicles.models.Vehicle;

public class CreateVehicleDTO implements ToEntity<Vehicle> {
    @NotNull(message = "It's required to send a model")
    @NotBlank(message = "It's required to send a model")
    private String model;

    @NotNull(message = "It's required to send a daily rate")
    private Float dailyRate;

    // #region Getters and Setters

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(float dailyRate) {
        this.dailyRate = dailyRate;
    }

    // #endregion

    @Override
    public Vehicle toEntity() {
        Vehicle entity = new Vehicle();

        entity.setModel(this.model);
        entity.setDailyRate(this.dailyRate);

        return entity;
    }
}
