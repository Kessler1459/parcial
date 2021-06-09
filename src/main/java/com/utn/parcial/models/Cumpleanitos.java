package com.utn.parcial.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@RequiredArgsConstructor
public class Cumpleanitos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Persona cumplaniero;

    @ManyToMany(mappedBy = "invitedTo")
    @ToString.Exclude
    private Set<Persona> invitados;
}
