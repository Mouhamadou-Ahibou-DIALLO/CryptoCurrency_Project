package com.cryptocurrency.data.controller;

import com.cryptocurrency.data.model.CryptoPriceHistory;
import com.cryptocurrency.data.service.DataCollectionService;
import com.cryptocurrency.data.service.PredictionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The PredictionControllerTest class is a JUnit test class for the PredictionController class.
 * Author: Mouhamadou Ahibou DIALLO
 */
@ExtendWith(MockitoExtension.class)
public class PredictionControllerTest {

    /**
     * the dataCollectionService
     */
    @Mock
    private DataCollectionService dataCollectionService;

    /**
     * the predictionService
     */
    @Mock
    private PredictionService predictionService;

    /**
     * the predictionController
     */
    @InjectMocks
    private PredictionController predictionController;

    /**
     * the priceHistoryList
     */
    private List<CryptoPriceHistory> priceHistoryList;

    /**
     * Sets up the priceHistoryList variable with three CryptoPriceHistory objects with
     * prices 100.0, 105.0, and 110.0, with timestamps 5, 4, and 3 days before the current
     * date and time, respectively.
     * This method is annotated with @BeforeEach, which means it is executed before each test is run.
     * It sets up the priceHistoryList, which is used in the testGetLinearRegression, testGetMovingAverage,
     * and testGetMargingError test methods.
     */
    @BeforeEach
    public void setUp() {
        priceHistoryList = Arrays.asList(
                new CryptoPriceHistory(LocalDateTime.now().minusDays(5), 100.0),
                new CryptoPriceHistory(LocalDateTime.now().minusDays(4), 105.0),
                new CryptoPriceHistory(LocalDateTime.now().minusDays(3), 110.0)
        );
    }

    /**
     * Tests the getLinearRegression method of the PredictionController class.
     * Verifies that the getLinearRegression method returns a ResponseEntity with
     * status OK (200) when the DataCollectionService is mocked to return a valid list of
     * CryptoPriceHistory objects for the given cryptocurrency name.
     */
    @Test
    public void testGetLinearRegression_Success() {
        try (MockedStatic<DataCollectionService> mockedService = Mockito.mockStatic(DataCollectionService.class)) {
            mockedService.when(DataCollectionService::getCryptoPriceHistoryMap)
                    .thenReturn(Map.of("bitcoin", priceHistoryList));

            ResponseEntity<List<CryptoPriceHistory>> response = predictionController.getLinearRegression("bitcoin", "2023-01-01T00:00:00", "2023-12-31T23:59:59");
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }
    }

    /**
     * Tests the getMovingAverage method of the PredictionController class.
     * Verifies that the getMovingAverage method returns a ResponseEntity with
     * status OK (200) when the DataCollectionService is mocked to return a valid list of
     * CryptoPriceHistory objects for the given cryptocurrency name.
     */
    @Test
    public void testGetMovingAverage_Success() {
        try (MockedStatic<DataCollectionService> mockedService = Mockito.mockStatic(DataCollectionService.class)) {
            mockedService.when(DataCollectionService::getCryptoPriceHistoryMap)
                    .thenReturn(Map.of("bitcoin", priceHistoryList));

            ResponseEntity<List<CryptoPriceHistory>> response = predictionController.getMovingAverage("bitcoin", "2023-01-01T00:00:00", "2023-12-31T23:59:59");
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }
    }

    /**
     * Tests the getMargingError method of the PredictionController class.
     * Verifies that the getMargingError method returns a ResponseEntity with
     * status OK (200) when the DataCollectionService is mocked to return a
     * valid list of CryptoPriceHistory objects for the given cryptocurrency name.
     */
    @Test
    public void testGetMargingError_Success() {
        try (MockedStatic<DataCollectionService> mockedService = Mockito.mockStatic(DataCollectionService.class)) {
            mockedService.when(DataCollectionService::getCryptoPriceHistoryMap)
                    .thenReturn(Map.of("bitcoin", priceHistoryList));

            ResponseEntity<List<CryptoPriceHistory>> response = predictionController.getMargingError("bitcoin", "2023-01-01T00:00:00", "2023-12-31T23:59:59");
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }
    }
}
