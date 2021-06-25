package com.utn.parcial.models;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private  CurrencyType currencyType;
    @NotNull
    @Min(0)
    private Float monto;
    @OneToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

}
