package com.utn.parcial.controllers;

import com.utn.parcial.PostResponse;
import com.utn.parcial.models.Cumpleanitos;
import com.utn.parcial.models.Factura;
import com.utn.parcial.services.CumpleService;
import com.utn.parcial.util.EntityUrlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("cumples")
public class CumpleController {
    private final CumpleService cumpleService;

    @Autowired
    public CumpleController(CumpleService cumpleService) {
        this.cumpleService = cumpleService;
    }

    @PostMapping
    public ResponseEntity<PostResponse> addCumple(@RequestBody Cumpleanitos cumpleanitos) {
        cumpleanitos = cumpleService.addCumple(cumpleanitos);
        PostResponse pr = new PostResponse(EntityUrlBuilder.buildURL("cumples", cumpleanitos.getId().toString()), HttpStatus.CREATED.getReasonPhrase());
        return ResponseEntity.created(URI.create(pr.getUrl())).body(pr);
    }

    @GetMapping("/{idCumple}/factura")
    public ResponseEntity<List<Factura>> findFacturaFromCumple(@PathVariable Integer idCumple) {
        List<Factura> f = cumpleService.findFacturaFromCumple(idCumple);
        return ResponseEntity.status(f.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK).body(f);
    }

    @PutMapping("/{idCumple}/cumplaniero/{idPersona}")
    public ResponseEntity<Cumpleanitos> putCumpleaniero(@PathVariable Integer idCumple, @PathVariable Integer idPersona) {
        Cumpleanitos cumpleanitos = cumpleService.putCumpleaniero(idCumple, idPersona);
        return ResponseEntity.ok(cumpleanitos);
    }

    @PutMapping("/{idCumple}/invitados/{idInvitado}")
    public ResponseEntity<Cumpleanitos> addInvitado(@PathVariable Integer idCumple, @PathVariable Integer idInvitado) {
        Cumpleanitos cumpleanitos = cumpleService.addInvitado(idCumple, idInvitado);
        return ResponseEntity.ok(cumpleanitos);
    }
}
