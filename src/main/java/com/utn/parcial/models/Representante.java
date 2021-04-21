package com.utn.parcial.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Representante extends Persona {
    @OneToMany
    private List<Jugador> jugadores;
    private Float pesoDeLaBoveda;
    private Float montoTotal;

    public Representante() {
        this.jugadores = new ArrayList<>();
        this.pesoDeLaBoveda =(float)0;
        this.montoTotal =(float)0;
    }

    @Override
    PersonType personType() {
        return PersonType.REPRESENTANTE;
    }

    public Float getMontoTotal() {
        return (float) getJugadores().stream().mapToDouble(ju -> ju.getCurrency().getMonto() * ju.getCurrency().getCurrencyType().getValue()).sum();
    }

    public Float getPesoDeLaBoveda() {
        return this.getMontoTotal()/100;
    }
}
