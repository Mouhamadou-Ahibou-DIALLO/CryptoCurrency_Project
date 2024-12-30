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

    /**
     * Returns the moving average of the given cryptocurrency over the given period.
     * <p>
     * The returned value is the average price of the given cryptocurrency over the given period.
     * The period is specified in terms of the number of data points to use for the moving average.
     * <p>
     * If there is no data available for the requested cryptocurrency, an
     * {@link IllegalArgumentException} is thrown.
     *
     * @param name The name of the cryptocurrency to retrieve the moving average for.
     * @param period The number of data points to use for the moving average.
     * @return The moving average of the given cryptocurrency over the given period.
     * @throws IllegalArgumentException If there is no data available for the requested cryptocurrency.
     */
    @GetMapping("/moving-average")
    public double getMovingAverage(@RequestParam String name, @RequestParam int period) {
        List<CryptoPriceHistory> history = cryptoPriceHistoryMap.get(name);
        if (history == null) {
            throw new IllegalArgumentException("No data available for the requested cryptocurrency.");
        }
        return PredictionService.calculateMovingAverage(history, period);
    }

    /**
     * Returns the predicted next price of a cryptocurrency using linear regression.
     * <p>
     * This method retrieves the historical price data for the specified cryptocurrency
     * and applies a linear regression model to predict the next price.
     * <p>
     * If there is no data available for the requested cryptocurrency, an
     * {@link IllegalArgumentException} is thrown.
     *
     * @param name The name of the cryptocurrency to predict the next price for.
     * @return The predicted next price of the cryptocurrency using linear regression.
     * @throws IllegalArgumentException If there is no data available for the requested cryptocurrency.
     */
    @GetMapping("/linear-regression")
    public double getLinearRegressionPrediction(@RequestParam String name) {
        List<CryptoPriceHistory> history = cryptoPriceHistoryMap.get(name);
        if (history == null) {
            throw new IllegalArgumentException("No data available for the requested cryptocurrency.");
        }
        return PredictionService.predictNextPriceUsingLinearRegression(history);
    }

    /**
     * Returns the error margin of a predicted price of a cryptocurrency.
     * <p>
     * The error margin is calculated as the absolute difference between the predicted price
     * and the average of the actual prices, divided by the average of the actual prices,
     * and expressed as a percentage.
     * <p>
     * The method retrieves the historical price data for the specified cryptocurrency
     * and applies the error margin calculation.
     * <p>
     * If there is no data available for the requested cryptocurrency, an
     * {@link IllegalArgumentException} is thrown.
     *
     * @param name The name of the cryptocurrency to calculate the error margin for.
     * @param predictedPrice The predicted price of the cryptocurrency.
     * @return The error margin of the predicted price expressed as a percentage.
     * @throws IllegalArgumentException If there is no data available for the requested cryptocurrency.
     */
    @GetMapping("/error-margin")
    public double getErrorMargin(@RequestParam String name, @RequestParam double predictedPrice) {
        List<Double> history = DataCollectionService.getPriceHistory(name);
        return PredictionService.calculateErrorMargin(history, predictedPrice);
    }
}
