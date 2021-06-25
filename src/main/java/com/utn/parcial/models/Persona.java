package com.utn.parcial.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(property = "personType",visible = true,use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Representante.class,name = "REPRESENTANTE"),
        @JsonSubTypes.Type(value = Jugador.class,name = "JUGADOR")
})
public abstract class Persona {
    public Persona(Integer id, String name, String lastName, List<Cumpleanitos> cumples, Currency currency, Set<Cumpleanitos> invitedTo) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.cumples = cumples;
        this.currency = currency;
        this.invitedTo = invitedTo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Size(min = 2, max = 50)
    private String name;
    @NotNull
    @Size(min = 2, max = 50)
    private String lastName;
    @NotNull
    abstract PersonType personType();
    @OneToMany(mappedBy = "cumplaniero",cascade = CascadeType.ALL,orphanRemoval = true)
    @ToString.Exclude
    @JsonBackReference
    private List<Cumpleanitos> cumples;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "persona")
    private Currency currency;
    @JsonIgnore
    @ManyToMany(mappedBy = "invitados")
    private Set<Cumpleanitos> invitedTo;

}
