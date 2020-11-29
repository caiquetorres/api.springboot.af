package com.app.vehicles.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.app.common.ModifyEntity;
import com.app.vehicles.models.Vehicle;

public class UpdateVehicleDTO implements ModifyEntity<Vehicle> {
    @NotNull(message = "It's required to send a daily rate")
    @Min(message = "The number must be greater than or equal to 0", value = 0)
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
