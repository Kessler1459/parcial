package com.utn.parcial.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@SuperBuilder
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

    @NotNull
    private LocalDate fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "representante_id")
    private Representante representante;

    @Override
    PersonType personType() {
        return PersonType.JUGADOR;
    }
}
