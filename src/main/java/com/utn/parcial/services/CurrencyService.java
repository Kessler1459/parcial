package com.utn.parcial.services;

import com.utn.parcial.models.Currency;
import com.utn.parcial.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CurrencyService {
    private CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }

    public Currency addCurrency(Currency c) {
        return currencyRepository.save(c);
    }

    public Currency findById(Integer id) {
        return currencyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Currency not found"));
    }

    public void deleteCurrency(Integer currencyId) {
        currencyRepository.deleteById(currencyId);
    }
}
