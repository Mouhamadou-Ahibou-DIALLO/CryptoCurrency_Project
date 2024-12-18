package com.cryptocurrency.data.controller;

import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.model.MarketData;
import com.cryptocurrency.data.service.MarketDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The MarketDataController class is a Spring REST controller for managing market data.
 * Author: Mouhamadou Ahibou DIALLO
 */
@RestController
@RequestMapping("/api/marketdata")
public class MarketDataController {

    /**
     * The marketDataService field is a Spring service for MarketData objects.
     */
    @Autowired
    private MarketDataService marketDataService;

    /**
     * This method returns a list of all MarketData objects in the database.
     * @return a list of MarketData objects
     */
    @GetMapping
    public ResponseEntity<List<MarketData>> getAllMarketData() {
        List<MarketData> marketData = marketDataService.findAll();
        return ResponseEntity.ok(marketData);
    }

    /**
     * This method returns a MarketData object by its ID.
     * @param id the ID of the MarketData object to retrieve
     * @return a MarketData object if found, or a 404 NOT FOUND response if not
     */
    @GetMapping("/{id}")
    public ResponseEntity<MarketData> getMarketDataById(@PathVariable Long id) {
        MarketData marketData = marketDataService.findById(id);
        if (marketData == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(marketData);
    }

    /**
     * This method returns a list of all MarketData objects for a given CryptoCurrency.
     *
     * @param cryptoCurrency the CryptoCurrency to find MarketData for
     * @return a list of MarketData objects for the given CryptoCurrency
     */
    @GetMapping("/crypto")
    public ResponseEntity<MarketData> getMarketDataByCryptoId(CryptoCurrency cryptoCurrency) {
        MarketData marketData = marketDataService.findByCryptoCurrency(cryptoCurrency);
        if (cryptoCurrency == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(marketData);
    }
}
