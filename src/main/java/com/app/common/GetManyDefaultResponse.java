package com.app.common;

import java.util.List;

public class GetManyDefaultResponse<TValue> {
    private int total;
    private List<TValue> elements;

    // #region Getters and Setters

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TValue> getElements() {
        return elements;
    }

    public void setElements(List<TValue> elements) {
        this.elements = elements;
    }

    // #endregion
}
