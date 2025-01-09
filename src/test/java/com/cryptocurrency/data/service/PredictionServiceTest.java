package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.CryptoPriceHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The PredictionServiceTest class is a JUnit test class for the PredictionService class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class PredictionServiceTest {

    /**
     * The cryptoPriceHistoryList variable is a list of CryptoPriceHistory objects.
     */
    List<CryptoPriceHistory> cryptoPriceHistoryList;

    /**
     * Initializes the cryptoPriceHistoryList variable with two CryptoPriceHistory objects.
     *
     * This method is annotated with @BeforeEach, which means it is executed before each test is run.
     * It sets up the cryptoPriceHistoryList, which is used in the testCalculateMovingAverages and
     * testCalculateErrorMargins test methods.
     */
    @BeforeEach
    public void setUp() {
        CryptoPriceHistory cryptoPriceHistory = new CryptoPriceHistory(LocalDateTime.now(), 100.0);
        CryptoPriceHistory cryptoPriceHistory1 = new CryptoPriceHistory(LocalDateTime.now(), 200.0);

        cryptoPriceHistoryList = List.of(cryptoPriceHistory, cryptoPriceHistory1);
    }

    /**
     * Tests the calculateMovingAverages method of the PredictionService class.
     *
     * This test verifies that the calculateMovingAverages method returns a non-empty list
     * and that the calculated moving averages do not exceed the maximum price value
     * present in the cryptoPriceHistoryList.
     */
    @Test
    public void testCalculateMovingAverages() {
        List<CryptoPriceHistory> movingAverages = PredictionService.calculateMovingAverages(cryptoPriceHistoryList);
        assert !movingAverages.isEmpty();

        assertTrue(movingAverages.get(0).getPrice() <= 200);
        assertTrue(movingAverages.get(1).getPrice() <= 200);
    }

    /**
     * Tests the predictNextPricesUsingLinearRegression method of the PredictionService class.
     *
     * This test verifies that the predictNextPricesUsingLinearRegression method returns a non-empty list
     * of predicted prices when provided with a valid cryptoPriceHistoryList containing at least two data points.
     */
    @Test
    public void testPredictionNextPricesUsingLinearRegression() {
        List<CryptoPriceHistory> predictedPrices = PredictionService.predictNextPricesUsingLinearRegression(cryptoPriceHistoryList);
        assert !predictedPrices.isEmpty();
    }

    /**
     * Tests the calculateErrorMargins method of the PredictionService class.
     *
     * This test verifies that the calculateErrorMargins method returns a non-empty list
     * of error margins when provided with valid lists of actual and predicted prices.
     */
    @Test
    public void testCalculateErrorMargins() {
        List<CryptoPriceHistory> errorMargins = PredictionService.calculateErrorMargins(cryptoPriceHistoryList, cryptoPriceHistoryList);
        assert !errorMargins.isEmpty();
    }
}
