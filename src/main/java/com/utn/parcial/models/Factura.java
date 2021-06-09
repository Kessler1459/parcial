package com.utn.parcial.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Factura {
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("amount")
    private float amount;
}
