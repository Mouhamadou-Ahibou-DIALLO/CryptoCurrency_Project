package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.CryptoPriceHistory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The PredictionService class provides methods for making predictions and calculating error margins.
 * Author: Mouhamadou Ahibou DIALLO
 */
@Service
public class PredictionService {

    /**
     * Default constructor for the PredictionService class.
     */
    public PredictionService() {}

    /**
     * Calculates the moving averages of the given cryptocurrency over the given period.
     * This method retrieves the historical price data for the specified cryptocurrency
     * and applies a moving average model to calculate the moving averages.
     *
     * @param priceHistory A list of CryptoPriceHistory objects containing
     *                     historical price data. Must contain at least two data points.
     * @return A list of up to 10 moving averages for the cryptocurrency.
     * @throws IllegalArgumentException If the list contains fewer than two data points.
     */
    public static List<CryptoPriceHistory> calculateMovingAverages(List<CryptoPriceHistory> priceHistory) {
        int numberOfPoints = 10;
        System.out.println("Calculating moving averages for selected range");

        List<CryptoPriceHistory> movingAverages = new ArrayList<>();

        if (priceHistory.isEmpty()) {
            return movingAverages;
        }

        int interval = Math.max(1, priceHistory.size() / numberOfPoints);

        for (int i = 0; i < priceHistory.size(); i += interval) {
            double sum = 0;
            int count = 0;

            for (int j = i; j >= 0 && j > i - 5; j--) {
                sum += priceHistory.get(j).getPrice();
                count++;
            }

            movingAverages.add(new CryptoPriceHistory(priceHistory.get(i).getTimestamp(), sum / count));
        }

        return movingAverages;
    }

    /**
     * Predicts the next 5 prices of a cryptocurrency using linear regression.
     *
     * This method retrieves the historical price data for the specified cryptocurrency
     * and applies a linear regression model to predict the next 5 prices.
     *
     * @param priceHistory A list of CryptoPriceHistory objects containing
     *                     historical price data. Must contain at least two data points.
     * @return A list of up to 10 predicted prices for the cryptocurrency.
     * @throws IllegalArgumentException If the list contains fewer than two data points.
     */
    public static List<CryptoPriceHistory> predictNextPricesUsingLinearRegression(List<CryptoPriceHistory> priceHistory) {
        int numberOfPoints = 10;
        System.out.println("Predicting prices using linear regression");

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

        int interval = Math.max(1, n / numberOfPoints);

        List<CryptoPriceHistory> predictedPrices = new ArrayList<>();

        for (int i = 1; i <= numberOfPoints; i++) {
            int index = i * interval;
            double predictedPrice = slope * index + intercept;
            predictedPrices.add(new CryptoPriceHistory(
                    priceHistory.get(0).getTimestamp().plusDays(index),
                    predictedPrice
            ));
        }

        return predictedPrices;
    }

    /**
     * Calculates the error margins between actual and predicted prices of a cryptocurrency.
     *
     * This method shuffles the provided lists of actual and predicted prices and calculates
     * the error margins for up to 10 data points. The error margin is the absolute percentage
     * difference between the actual and predicted price.
     *
     * @param actualPrices A list of CryptoPriceHistory objects representing actual historical prices.
     * @param predictedPrices A list of CryptoPriceHistory objects representing predicted prices.
     * @return A list of CryptoPriceHistory objects where each contains the timestamp of the actual
     *         price and the error margin expressed as a percentage.
     */
    public static List<CryptoPriceHistory> calculateErrorMargins(List<CryptoPriceHistory> actualPrices, List<CryptoPriceHistory> predictedPrices) {
        int numberOfPoints = 10;
        System.out.println("Calculating error margins");

        if (actualPrices.isEmpty() || predictedPrices.isEmpty()) {
            throw new IllegalArgumentException("Actual or predicted price list is empty.");
        }

        int n = Math.min(actualPrices.size(), predictedPrices.size());
        int interval = Math.max(1, n / numberOfPoints);

        List<CryptoPriceHistory> errorMargins = new ArrayList<>();

        for (int i = 0; i < n; i += interval) {
            double actualPrice = actualPrices.get(i).getPrice();
            double predictedPrice = predictedPrices.get(i).getPrice();

            double margin = Math.abs((predictedPrice - actualPrice) / actualPrice) * 100;
            errorMargins.add(new CryptoPriceHistory(actualPrices.get(i).getTimestamp(), margin));

            if (errorMargins.size() >= numberOfPoints) {
                break;
            }
        }

        return errorMargins;
    }
}
