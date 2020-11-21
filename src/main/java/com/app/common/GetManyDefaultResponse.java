package com.app.common;

import java.util.List;

public class GetManyDefaultResponse<TProxy> {
    private int total;
    private List<TProxy> elements;

    // #region Getters and Setters

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TProxy> getElements() {
        return elements;
    }

    public void setElements(List<TProxy> elements) {
        this.elements = elements;
    }

    // #endregion
}
