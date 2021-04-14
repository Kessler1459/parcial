package com.utn.parcial.models;

public enum CurrencyType {
    EURO("Euros"),
    DOLAR("Dolares");

    private String code;

    CurrencyType(String code) {
        this.code = code;
    }
}
