package com.app.vehicles.dto;

import javax.validation.constraints.NotNull;
import com.app.common.ModifyEntity;
import com.app.vehicles.models.Vehicle;

public class UpdateVehicleDTO implements ModifyEntity<Vehicle> {
    @NotNull(message = "It's required to send a daily rate")
    private Float dailyRate;

    // #region Getters and Setters

    public float getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(float dailyRate) {
        this.dailyRate = dailyRate;
    }

    // #endregion

    @Override
    public void modifyEntity(Vehicle entity) {
        entity.setDailyRate(this.dailyRate);
    }
}
