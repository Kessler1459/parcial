package com.utn.parcial.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@RequiredArgsConstructor
public class Cumpleanitos {
    public Cumpleanitos(LocalDate fecha) {
        this.fecha = fecha;
        invitados=new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Persona cumplaniero;

    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Persona> invitados;

    public void addInvitado(Persona p){
        invitados.add(p);
    }
}
