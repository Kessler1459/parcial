package com.utn.parcial.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Representante extends Persona{
    @OneToMany
    private List<Jugador> jugadores;
    private Float pesoDeLaBoveda;
    private Float montoTotal;

    @Override
    PersonType personType() {
        return PersonType.REPRESENTANTE;
    }
}
