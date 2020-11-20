package com.app.vehicles.dto;

import com.app.common.ModifyEntity;
import com.app.vehicles.models.Vehicle;

public class UpdateVehicleDTO implements ModifyEntity<Vehicle> {
    private float dailyRate;
    private boolean isRented;

    // #region Getters and Setters

    public float getDailyRate() {
        return dailyRate;
    }

    public boolean getIsRented() {
        return isRented;
    }

    public void setIsRented(boolean isRented) {
        this.isRented = isRented;
    }

    public void setDailyRate(float dailyRate) {
        this.dailyRate = dailyRate;
    }

    // #endregion

    @Override
    public void modifyEntity(Vehicle entity) {
        entity.setDailyRate(this.dailyRate);
        entity.setRented(this.isRented);
    }
}
