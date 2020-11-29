package com.app.vehicles.models;

import com.app.common.BaseEntity;
import com.app.common.ToDTO;
import com.app.vehicles.dto.GetVehicleDTO;

public class Vehicle extends BaseEntity implements ToDTO<GetVehicleDTO> {
    private String model;
    private float dailyRate;

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
    public GetVehicleDTO toDto() {
        GetVehicleDTO dto = new GetVehicleDTO();

        dto.setId(this.id);
        dto.setModel(this.model);
        dto.setDailyRate(this.dailyRate);

        return dto;
    }
}
