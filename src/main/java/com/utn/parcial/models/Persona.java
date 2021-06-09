package com.utn.parcial.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(property = "personType",visible = true,use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Representante.class,name = "REPRESENTANTE"),
        @JsonSubTypes.Type(value = Jugador.class,name = "JUGADOR")
})
public abstract class Persona {
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
    private List<Cumpleanitos> cumples;
    @OneToOne(cascade = CascadeType.ALL)
    private Currency currency;

    @ManyToMany
    private Set<Cumpleanitos> invitedTo;

}
