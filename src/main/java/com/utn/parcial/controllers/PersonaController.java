package com.utn.parcial.controllers;

import com.utn.parcial.PostResponse;
import com.utn.parcial.models.Currency;
import com.utn.parcial.models.Persona;
import com.utn.parcial.services.PersonaService;
import com.utn.parcial.util.EntityUrlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/personas")
public class PersonaController {
    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping
    public ResponseEntity<List<Persona>> findAll() {
        final List<Persona> l = personaService.findAll();
        return ResponseEntity.status(l.isEmpty() ? NO_CONTENT : OK).body(l);
    }

    @GetMapping("/representantes")
    public ResponseEntity<List<Persona>> findAllRepresentantes() {
        final List<Persona> l = personaService.findAllRepresentantes();
        return ResponseEntity.status(l.isEmpty() ? NO_CONTENT : OK).body(l);
    }

    @GetMapping("/jugadores")
    public ResponseEntity<List<Persona>> findAllJugadores() {
        final List<Persona> l = personaService.findAllJugadores();
        return ResponseEntity.status(l.isEmpty() ? NO_CONTENT : OK).body(l);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(personaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PostResponse> addPersona(@RequestBody Persona p) {
        p = personaService.addPersona(p);
        final PostResponse pr = new PostResponse(EntityUrlBuilder.buildURL("personas", p.getId().toString()), CREATED.getReasonPhrase());
        return ResponseEntity.created(URI.create(pr.getUrl())).body(pr);
    }

    @PutMapping("/{repId}/jugadores/{jugId}")
    public ResponseEntity<Persona> addJugadorToRepresentante(@PathVariable Integer repId, @PathVariable Integer jugId) {
        return ResponseEntity.accepted().body(personaService.addJugadorToRepresentante(repId, jugId));
    }

    @PutMapping("/{idJugador}/currencies/{idCurrency}")
    public ResponseEntity<Persona> addCurrencyToJugador(@PathVariable Integer idJugador, @PathVariable Integer idCurrency) {
        return ResponseEntity.accepted().body(personaService.addCurrencyToJugador(idJugador, idCurrency));
    }

    @DeleteMapping
    public ResponseEntity<Object> deletePerson(@RequestParam Integer idPerson) {
        personaService.deletePerson(idPerson);
        return ResponseEntity.status(OK).build();
    }
}
