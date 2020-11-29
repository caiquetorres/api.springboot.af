package com.app.reserve.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;
import com.app.common.ToEntity;
import com.app.reserve.models.Reserve;

public class CreateReserveDTO implements ToEntity<Reserve> {
    @NotNull(message = "It's required to send a valid date")
    private Date from;

    @NotNull(message = "It's required to send a valid date")
    private Date to;

    // #region Getters and Setters

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
