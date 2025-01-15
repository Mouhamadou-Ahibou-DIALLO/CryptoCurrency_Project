package com.cryptocurrency.data.controller;

import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.model.CryptoPriceHistory;
import com.cryptocurrency.data.service.CryptoCurrencyService;

import com.cryptocurrency.data.service.DataCollectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The CryptoCurrencyControllerTest class is a JUnit test class for the CryptoCurrencyController class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class CryptoCurrencyControllerTest {

    /**
     * The cryptoCurrencyController variable is an instance of the CryptoCurrencyController class.
     */
    private CryptoCurrencyController cryptoCurrencyController;

    /**
     * The cryptoCurrencyService variable is an instance of the CryptoCurrencyService class.
     */
    private CryptoCurrencyService cryptoCurrencyService;

    @Mock
    private DataCollectionService dataCollectionService;

    /**
     * Sets up the test environment before each test.
     * Mocks the CryptoCurrencyService and injects it into the CryptoCurrencyController.
     */
    @BeforeEach
    public void setUp() {
        cryptoCurrencyService = Mockito.mock(CryptoCurrencyService.class);
        dataCollectionService = Mockito.mock(DataCollectionService.class);
        cryptoCurrencyController = new CryptoCurrencyController();
        ReflectionTestUtils.setField(cryptoCurrencyController, "cryptoCurrencyService", cryptoCurrencyService);
    }

    /**
     * Tests the getAllCryptocurrencies method of the CryptoCurrencyController class.
     * Ensures that the method returns a list of all the cryptocurrencies in the database.
     */
    @Test
    public void getAllCryptocurrencies_ShouldReturnListOfCryptocurrencies() {
        CryptoCurrency crypto1 = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        CryptoCurrency crypto2 = new CryptoCurrency(2L, "Ethereum", "ETH", 2);

        List<CryptoCurrency> mockCryptos = Arrays.asList(crypto1, crypto2);
        when(cryptoCurrencyService.getAllCryptoCurrency()).thenReturn(mockCryptos);
        ResponseEntity<List<CryptoCurrency>> response = cryptoCurrencyController.getAllCryptocurrencies();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        assertEquals("Bitcoin", response.getBody().get(0).getName());
    }

    /**
     * Tests the getCryptoCurrencyById method of the CryptoCurrencyController class.
     * Ensures that the method returns a CryptoCurrency object by its ID.
     */
    @Test
    public void getCryptoCurrencyById_ShouldReturnCryptoCurrency() {
        CryptoCurrency mockCrypto = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        when(cryptoCurrencyService.getCryptoCurrencyById(1L)).thenReturn(mockCrypto);

        ResponseEntity<CryptoCurrency> response = cryptoCurrencyController.getCryptoCurrencyById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Bitcoin", Objects.requireNonNull(response.getBody()).getName());
        assertEquals("BTC", response.getBody().getSymbol());
    }

    /**
     * Tests the searchCrypto method of the CryptoCurrencyController class.
     * Ensures that the method returns a CryptoCurrency object by its name.
     */
    @Test
    public void testSearchCrypto() {
        CryptoCurrency mockCrypto = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        when(cryptoCurrencyService.getCryptoCurrencyByName("Bitcoin")).thenReturn(mockCrypto);
        ResponseEntity<?> response = cryptoCurrencyController.searchCrypto("Bitcoin");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    /**
     * Tests the searchCrypto method of the CryptoCurrencyController class.
     * Ensures that the method returns a 404 status when the CryptoCurrency is not found.
     */
    @Test
    public void testSearchCryptoNotFound() {
        when(cryptoCurrencyService.getCryptoCurrencyByName("Bitcoin")).thenReturn(null);
        ResponseEntity<?> response = cryptoCurrencyController.searchCrypto("Bitcoin");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    /**
     * Tests the getCryptoCurrencyByName method of the CryptoCurrencyController class.
     * Ensures that the method returns a CryptoCurrency object by its name.
     */
    @Test
    public void testGetCryptoByName() {
        CryptoCurrency mockCrypto = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        when(cryptoCurrencyService.getCryptoCurrencyByName("Bitcoin")).thenReturn(mockCrypto);
        ResponseEntity<?> response = cryptoCurrencyController.getCryptoCurrencyByName("Bitcoin");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    /**
     * Tests the getPriceHistory method of the CryptoCurrencyController class.
     * Ensures that the method returns a list of CryptoPriceHistory objects within the given date range.
     * The list should contain all the price history entries for the given cryptocurrency name,
     * and should be ordered by timestamp.
     */
    @Test
    public void testGetPriceHistory() {
        String cryptoName = "Bitcoin";
        String start = "2024-01-01T00:00:00";
        String end = "2024-01-02T00:00:00";
        LocalDateTime startDate = LocalDateTime.parse(start);
        LocalDateTime endDate = LocalDateTime.parse(end);

        List<CryptoPriceHistory> mockPriceHistory = List.of(
                new CryptoPriceHistory(startDate.minusDays(1), 30000.0),
                new CryptoPriceHistory(startDate, 31000.0),
                new CryptoPriceHistory(startDate.plusHours(12), 32000.0),
                new CryptoPriceHistory(endDate.plusDays(1), 33000.0)
        );

        try (MockedStatic<DataCollectionService> mockedService = Mockito.mockStatic(DataCollectionService.class)) {
            mockedService.when(DataCollectionService::getCryptoPriceHistoryMap)
                    .thenReturn(Map.of(cryptoName, mockPriceHistory));

            ResponseEntity<List<CryptoPriceHistory>> response = cryptoCurrencyController.getPriceHistory(cryptoName, start, end);

            assertEquals(response.getStatusCode(), HttpStatus.OK);
            assertNotNull(response.getBody());
            assertEquals(2, response.getBody().size());
            assertTrue(response.getBody().stream()
                    .allMatch(entry -> !entry.getTimestamp().isBefore(startDate) && !entry.getTimestamp().isAfter(endDate)));
        }
    }
}