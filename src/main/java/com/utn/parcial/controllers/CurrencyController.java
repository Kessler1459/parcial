package com.utn.parcial.controllers;

import com.utn.parcial.PostResponse;
import com.utn.parcial.models.Currency;
import com.utn.parcial.services.CurrencyService;
import com.utn.parcial.util.EntityUrlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {
    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public ResponseEntity<List<Currency>> findAll() {
        final List<Currency> l = currencyService.findAll();
        return ResponseEntity.status(l.isEmpty() ? NO_CONTENT : OK).body(l);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Currency> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(currencyService.findById(id));
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteCurrency(@RequestParam Integer currencyId) {
        currencyService.deleteCurrency(currencyId);
        return ResponseEntity.status(OK).build();
    }

    @PostMapping
    public ResponseEntity<PostResponse> addCurrency(@RequestBody Currency c) {
        c = currencyService.addCurrency(c);
        final PostResponse pr = new PostResponse(EntityUrlBuilder.buildURL("currencies", c.getId().toString()), CREATED.getReasonPhrase());
        return ResponseEntity.created(URI.create(pr.getUrl())).body(pr);
    }
}
