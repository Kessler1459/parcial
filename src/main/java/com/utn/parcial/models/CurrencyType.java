package com.utn.parcial.models;

public enum CurrencyType {
    EURO((float)170),
    DOLAR((float)150);

    private Float value;

    CurrencyType(Float value) {
        this.value = value;
    }

    public Float getValue() {
        return value;
    }
}
