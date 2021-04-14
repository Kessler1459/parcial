package com.utn.parcial.repositories;

import com.utn.parcial.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona,Integer> {
    /**
     *
     * creo que es cochino pero no se me ocurria nada UwU
     */
    @Query(value = "SELECT * FROM persona p WHERE p.goles IS NULL;", nativeQuery = true)
    List<Persona> findAllRepresentantes();

    @Query(value = "SELECT * FROM persona p WHERE p.goles IS NOT NULL;", nativeQuery = true)
    List<Persona> findAllJugadores();
}
