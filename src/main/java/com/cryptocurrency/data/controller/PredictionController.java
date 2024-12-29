package com.cryptocurrency.data.controller;

import com.cryptocurrency.data.model.CryptoPriceHistory;
import com.cryptocurrency.data.service.DataCollectionService;
import com.cryptocurrency.data.service.PredictionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/predictions")
public class PredictionController {

    private final static Map<String, List<CryptoPriceHistory>> cryptoPriceHistoryMap = DataCollectionService.getCryptoPriceHistoryMap();
    private final PredictionService predictionService;

    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @GetMapping("/moving-average")
    public double getMovingAverage(@RequestParam String name, @RequestParam int period) {
        List<CryptoPriceHistory> history = cryptoPriceHistoryMap.get(name);
        if (history == null) {
            throw new IllegalArgumentException("No data available for the requested cryptocurrency.");
        }
        return predictionService.calculateMovingAverage(history, period);
    }

    @GetMapping("/linear-regression")
    public double getLinearRegressionPrediction(@RequestParam String name) {
        List<CryptoPriceHistory> history = cryptoPriceHistoryMap.get(name);
        if (history == null) {
            throw new IllegalArgumentException("No data available for the requested cryptocurrency.");
        }
        return predictionService.predictNextPriceUsingLinearRegression(history);
    }
}
