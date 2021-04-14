package com.utn.parcial.models;

public enum PersonType {
    REPRESENTANTE("Representante"),
    JUGADOR("Jugador");

    private String code;

    PersonType(String code) {
        this.code = code;
    }
}
