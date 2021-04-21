package com.utn.parcial.services;


import com.utn.parcial.exceptions.NotRepresentanteJugadorException;
import com.utn.parcial.models.*;
import com.utn.parcial.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PersonaService {
    private final PersonaRepository personaRepository;
    private final CurrencyService currencyService;

    @Autowired
    public PersonaService(PersonaRepository personaRepository, CurrencyService currencyService) {
        this.personaRepository = personaRepository;
        this.currencyService = currencyService;
    }

    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    public Persona addPersona(Persona p) {
        return personaRepository.save(p);
    }

    public void deletePerson(Integer idPerson) {
        personaRepository.deleteById(idPerson);
    }

    public Persona findById(Integer id) {
        return personaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found"));
    }

    public Persona addJugadorToRepresentante(Integer repId, Integer jugId) {
        Persona representante = findById(repId);
        Persona jugador = findById(jugId);
        if (!(representante instanceof Representante)) {
            throw new NotRepresentanteJugadorException("la id no corresponde a un representante");
        } else if (!(jugador instanceof Jugador)) {
            throw new NotRepresentanteJugadorException("la id no corresponde a un jugador");
        }
        ((Representante) representante).getJugadores().add((Jugador) jugador);
        return personaRepository.save(representante);
    }

    public Persona addCurrencyToJugador(Integer idJugador, Integer idCurrency) {
        Persona jugador = findById(idJugador);
        Currency currency = currencyService.findById(idCurrency);
        if (!(jugador instanceof Jugador)) {
            throw new NotRepresentanteJugadorException("la id no corresponde a un jugador");
        }
        ((Jugador) jugador).setCurrency(currency);
        return personaRepository.save(jugador);
    }

    public List<Persona> findAllRepresentantes() {
        return personaRepository.findAllRepresentantes();
    }

    public List<Persona> findAllJugadores() {
        return personaRepository.findAllJugadores();
    }
}
