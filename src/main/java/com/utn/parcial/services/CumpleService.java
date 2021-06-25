package com.utn.parcial.services;

import com.utn.parcial.exceptions.NotFoundException;
import com.utn.parcial.models.Cumpleanitos;
import com.utn.parcial.models.CurrencyType;
import com.utn.parcial.models.Factura;
import com.utn.parcial.models.Persona;
import com.utn.parcial.repositories.CumpleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CumpleService {
    static final Float PRICE_PESOS = 25000f;
    private final CumpleRepository cumpleRepository;
    private final ApiService apiService;
    private final PersonaService personaService;

    @Autowired
    public CumpleService(CumpleRepository cumpleRepository, ApiService apiService, PersonaService personaService) {
        this.cumpleRepository = cumpleRepository;
        this.apiService = apiService;
        this.personaService = personaService;
    }

    public Cumpleanitos addCumple(Cumpleanitos cumpleanitos) {
        return cumpleRepository.save(cumpleanitos);
    }

    public Cumpleanitos findById(Integer idCumple) {
        return cumpleRepository.findById(idCumple).orElseThrow(() -> new NotFoundException("Cumple not found"));
    }

    public List<Factura> findFacturaFromCumple(Integer idCumple) {
        final Cumpleanitos cumple = findById(idCumple);
        final Float euro = apiService.getEuro();
        final Float dolar = apiService.getDolar();
        List<Factura> facturas = new ArrayList<>();
        cumple.getInvitados()
                .forEach((persona) -> {
                    final CurrencyType currencyType = persona.getCurrency().getCurrencyType();
                    facturas.add(new Factura(persona.getName() + " " + persona.getLastName(), currencyType.name(), PRICE_PESOS / (currencyType.equals(CurrencyType.EURO) ? euro : dolar)));
                });
        return facturas;
    }

    public Cumpleanitos putCumpleaniero(Integer idCumple, Integer idPersona) {
        final Cumpleanitos c = findById(idCumple);
        final Persona p = personaService.findById(idPersona);
        c.setCumplaniero(p);
        return cumpleRepository.save(c);
    }

    public Cumpleanitos addInvitado(Integer idCumple, Integer idInvitado) {
        final Cumpleanitos c = findById(idCumple);
        final Persona p = personaService.findById(idInvitado);
        c.addInvitado(p);
        return cumpleRepository.save(c);
    }
}
