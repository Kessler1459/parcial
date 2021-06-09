package com.utn.parcial.services;

import com.utn.parcial.exceptions.NotFoundException;
import com.utn.parcial.models.Cumpleanitos;
import com.utn.parcial.models.Currency;
import com.utn.parcial.models.CurrencyType;
import com.utn.parcial.models.Factura;
import com.utn.parcial.repositories.CumpleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CumpleService {
    private final CumpleRepository cumpleRepository;
    private final ApiService apiService;

    @Autowired
    public CumpleService(CumpleRepository cumpleRepository, ApiService apiService) {
        this.cumpleRepository = cumpleRepository;
        this.apiService = apiService;
    }

    public Cumpleanitos addCumple(Cumpleanitos cumpleanitos) {
        return cumpleRepository.save(cumpleanitos);
    }

    public Cumpleanitos findById(Integer idCumple) {
        return cumpleRepository.findById(idCumple).orElseThrow(() -> new NotFoundException("Cumple not found"));
    }

    public List<Factura> findFacturaFromCumple(Integer idCumple) {
        final Cumpleanitos cumple = findById(idCumple);
        List<Factura> facturas = new ArrayList<>();
        cumple.getInvitados()
                .forEach((persona)-> {
                    final Currency c=persona.getCurrency();
                    facturas.add(new Factura(persona.getName()+" "+persona.getLastName(),c.getCurrencyType().name(),25000/(c.getCurrencyType().equals(CurrencyType.EURO)? apiService.getEuro() : apiService.getDolar())));
                });
        return facturas;
    }
}
