package com.app.reserve.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;
import com.app.common.ModifyEntity;
import com.app.reserve.models.Reserve;

public class UpdateReserveDTO implements ModifyEntity<Reserve> {
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
    public void modifyEntity(Reserve entity) {
        entity.setFrom(this.from);
        entity.setTo(this.to);
    }
}
