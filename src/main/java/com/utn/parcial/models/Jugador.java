package com.utn.parcial.models;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
public class Jugador extends Persona{
    @Min(10)
    @NotNull
    private Float peso;
    @Min(1)
    @NotNull
    private Float altura;
    @Min(0)
    @NotNull
    private Integer goles;
    @Min(0)
    @NotNull
    private Integer minutosJugados;
    @OneToOne(cascade = CascadeType.ALL)
    private Currency currency;
    @NotNull
    private LocalDate fechaNacimiento;

    @Override
    PersonType personType() {
        return PersonType.JUGADOR;
    }
}
