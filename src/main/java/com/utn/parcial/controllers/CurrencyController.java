package com.utn.parcial.controllers;

import com.utn.parcial.models.Currency;
import com.utn.parcial.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {
    private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public List<Currency> findAll(){
        return currencyService.findAll();
    }

    @GetMapping("/{id}")
    public Currency findById(@PathVariable Integer id){
        return currencyService.findById(id);
    }

    @DeleteMapping
    public void deleteCurrency(@RequestParam Integer currencyId){
        currencyService.deleteCurrency(currencyId);
    }

    @PostMapping
    public Currency addCurrency(@RequestBody Currency c){
        return currencyService.addCurrency(c);
    }
}
