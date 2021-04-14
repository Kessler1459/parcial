package com.utn.parcial.controllers;

import com.utn.parcial.models.Currency;
import com.utn.parcial.models.Persona;
import com.utn.parcial.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {
    private PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping
    public List<Persona> findAll(){
        return personaService.findAll();
    }

    @GetMapping("/representantes")
    public List<Persona> findAllRepresentantes(){
        return personaService.findAllRepresentantes();
    }

    @GetMapping("/jugadores")
    public List<Persona> findAllJugadores(){
        return personaService.findAllJugadores();
    }

    @GetMapping("/{id}")
    public Persona findById(@PathVariable Integer id){
        return personaService.findById(id);
    }

    @PostMapping
    public Persona addPersona(@RequestBody Persona p){
        return personaService.addPersona(p);
    }

    @PutMapping("/{repId}/jugadores/{jugId}")
    public Persona addJugadorToRepresentante(@PathVariable Integer repId,@PathVariable Integer jugId){
        return personaService.addJugadorToRepresentante(repId,jugId);
    }

    @PutMapping("/{idJugador}/currencies/{idCurrency}")
    public Persona addCurrencyToJugador(@PathVariable Integer idJugador, @PathVariable Integer idCurrency){
        return personaService.addCurrencyToJugador(idJugador,idCurrency);
    }

    @DeleteMapping
    public void deletePerson(@RequestParam Integer idPerson){
        personaService.deletePerson(idPerson);
    }
}
