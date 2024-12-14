package com.cryptocurrency.data.controller;

import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.service.CryptoCurrencyService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cryptocurrencies")
public class CryptoCurrencyController {

    @Autowired
    private CryptoCurrencyService cryptoCurrencyService;

    @GetMapping
    public ResponseEntity<List<CryptoCurrency>> getAllCryptocurrencies() {
        List<CryptoCurrency> cryptocurrencies = cryptoCurrencyService.getAllCryptoCurrency();
        return ResponseEntity.ok(cryptocurrencies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CryptoCurrency> getCryptoCurrencyById(@PathVariable Long id) {
        CryptoCurrency cryptocurrency = cryptoCurrencyService.getCryptoCurrencyById(id);
        return ResponseEntity.ok(cryptocurrency);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CryptoCurrency>> getCryptoCurrencyBySymbol(@PathVariable String symbol) {
        List<CryptoCurrency> cryptocurrency = cryptoCurrencyService.getCryptoCurrencyBySymbol(symbol);
        return ResponseEntity.ok(cryptocurrency);
    }
}

