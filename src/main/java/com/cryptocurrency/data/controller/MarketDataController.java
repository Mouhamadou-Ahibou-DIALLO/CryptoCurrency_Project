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

@RestController
@RequestMapping("/api/marketdata")
public class MarketDataController {

    @Autowired
    private MarketDataService marketDataService;

    @GetMapping
    public ResponseEntity<List<MarketData>> getAllMarketData() {
        List<MarketData> marketData = marketDataService.findAll();
        return ResponseEntity.ok(marketData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarketData> getMarketDataById(@PathVariable Long id) {
        MarketData marketData = marketDataService.findById(id);
        return ResponseEntity.ok(marketData);
    }

    @GetMapping("/crypto")
    public ResponseEntity<List<MarketData>> getMarketDataByCryptoId(CryptoCurrency cryptoCurrency) {
        List<MarketData> marketData = marketDataService.findByCryptoCurrency(cryptoCurrency);
        if (cryptoCurrency == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(marketData);
    }
}
