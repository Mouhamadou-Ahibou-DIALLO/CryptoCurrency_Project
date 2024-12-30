package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.CryptoPriceHistory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;

/**
 * The PredictionService class provides methods for making predictions and calculating error margins.
 * Author: Mouhamadou Ahibou DIALLO
 */
@Service
public class PredictionService {

    /**
     * Calculates the moving average for a given period.
     *
     * @param priceHistory A list of CryptoPriceHistory objects.
     * @param period The number of data points to use for the moving average.
     * @return The moving average for the given period.
     * @throws IllegalArgumentException If the given period exceeds the number of data points in priceHistory.
     */
    public static double calculateMovingAverage(List<CryptoPriceHistory> priceHistory, int period) {
        if (priceHistory.size() < period) {
            throw new IllegalArgumentException("Not enough data points for the given period.");
        }

        return priceHistory.stream()
                .mapToDouble(CryptoPriceHistory::getPrice)
                .skip(priceHistory.size() - period)
                .average()
                .orElse(0.0);
    }

/**
 * Predicts the next price of a cryptocurrency using linear regression.
 *
 * This method calculates the linear regression line (y = mx + c)
 * based on the given price history data points and predicts the
 * next price by extrapolating the line.
 *
 * @param priceHistory A list of CryptoPriceHistory objects containing
 *                     historical price data. Must contain at least two data points.
 * @return The predicted next price of the cryptocurrency.
 * @throws IllegalArgumentException If the list contains fewer than two data points.
 */
    public static double predictNextPriceUsingLinearRegression(List<CryptoPriceHistory> priceHistory) {
        if (priceHistory.size() < 2) {
            throw new IllegalArgumentException("Not enough data points for regression.");
        }

        int n = priceHistory.size();
        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0;

        for (int i = 0; i < n; i++) {
            double x = i + 1;
            double y = priceHistory.get(i).getPrice();

            sumX += x;
            sumY += y;
            sumXY += x * y;
            sumX2 += x * x;
        }

        double slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        double intercept = (sumY - slope * sumX) / n;

        return slope * (n + 1) + intercept;
    }

    /**
     * Calculates the error margin in percentage between the actual prices and a predicted price.
     *
     * The error margin is calculated as the absolute difference between the predicted price and the average of the actual prices, divided by the average of the actual prices, and expressed as a percentage.
     *
     * @param actualPrices A list of actual prices of a cryptocurrency.
     * @param predictedPrice The predicted price of the cryptocurrency.
     * @return The error margin in percentage.
     */
    public static double calculateErrorMargin(List<Double> actualPrices, double predictedPrice) {
        OptionalDouble avgActualPrice = actualPrices.stream().mapToDouble(Double::doubleValue).average();
        return avgActualPrice.isPresent() ? Math.abs((predictedPrice - avgActualPrice.getAsDouble()) / avgActualPrice.getAsDouble()) * 100 : 0.0;
    }
}

