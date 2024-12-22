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

/**
 * The CryptoCurrencyController class is a Spring REST controller for managing cryptocurrencies.
 * Author: Mouhamadou Ahibou DIALLO
 */
@RestController
@RequestMapping("/api/cryptocurrencies")
public class CryptoCurrencyController {

    /**
     * The service for CryptoCurrency objects.
     */
    @Autowired
    private CryptoCurrencyService cryptoCurrencyService;

    /**
     * Returns a list of all the cryptocurrencies in the database.
     *
     * @return A list of all the cryptocurrencies in the database.
     */
    @GetMapping
    public ResponseEntity<List<CryptoCurrency>> getAllCryptocurrencies() {
        List<CryptoCurrency> cryptocurrencies = cryptoCurrencyService.getAllCryptoCurrency();
        return ResponseEntity.ok(cryptocurrencies);
    }

    /**
     * Returns a CryptoCurrency object by its ID.
     *
     * @param id The ID of the CryptoCurrency to retrieve.
     * @return A CryptoCurrency object.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CryptoCurrency> getCryptoCurrencyById(@PathVariable Long id) {
        CryptoCurrency cryptocurrency = cryptoCurrencyService.getCryptoCurrencyById(id);
        return ResponseEntity.ok(cryptocurrency);
    }

    /**
     * Returns a CryptoCurrency object by its symbol.
     *
     * @param symbol The symbol of the CryptoCurrency to retrieve.
     * @return A CryptoCurrency object.
     */
    @GetMapping("/search/{symbol}")
    public ResponseEntity<CryptoCurrency> getCryptoCurrencyBySymbol(@PathVariable String symbol) {
        CryptoCurrency cryptocurrency = cryptoCurrencyService.getCryptoCurrencyBySymbol(symbol);
        return ResponseEntity.ok(cryptocurrency);
    }

    /**
     * Returns a CryptoCurrency object by its name.
     *
     * @param name The name of the CryptoCurrency to retrieve.
     * @return A CryptoCurrency object.
     */
    @GetMapping("/search/{name}")
    public ResponseEntity<CryptoCurrency> getCryptoCurrencyByName(@PathVariable String name) {
        CryptoCurrency cryptocurrency = cryptoCurrencyService.getCryptoCurrencyByName(name);
        return ResponseEntity.ok(cryptocurrency);
    }

    /**
     * Returns a CryptoCurrency object by its market capitalization rank.
     *
     * @param rank The market capitalization rank of the CryptoCurrency to retrieve.
     * @return A CryptoCurrency object.
     */
    @GetMapping("/search/{rank}")
    public ResponseEntity<CryptoCurrency> getCryptoCurrencyByMarketCapRank(@PathVariable int rank) {
        CryptoCurrency cryptocurrency = cryptoCurrencyService.getCryptoCurrencyByMarketCapRank(rank);
        return ResponseEntity.ok(cryptocurrency);
    }

    @GetMapping("/search/{price}")
    public ResponseEntity<List<CryptoCurrency>> getCryptoCurrencyByPrice(@PathVariable double price) {
        List<CryptoCurrency> cryptocurrency = cryptoCurrencyService.findByPrice(price);
        return ResponseEntity.ok(cryptocurrency);
    }
}

