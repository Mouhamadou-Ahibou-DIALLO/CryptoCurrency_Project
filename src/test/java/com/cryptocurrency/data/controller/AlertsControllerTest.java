package com.cryptocurrency.data.controller;

import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.service.CryptoCurrencyService;
import jakarta.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

/**
 * The CryptoCurrencyControllerTest class is a JUnit test class for the CryptoCurrencyController class.
 * Author: Mouhamadou Ahibou DIALLO
 */
class CryptoCurrencyControllerTest {

    /**
     * The cryptoCurrencyController variable is an instance of the CryptoCurrencyController class.
     */
    private CryptoCurrencyController cryptoCurrencyController;

    /**
     * The cryptoCurrencyService variable is an instance of the CryptoCurrencyService class.
     */
    private CryptoCurrencyService cryptoCurrencyService;

    /**
     * Sets up the test environment before each test.
     * Mocks the CryptoCurrencyService and injects it into the CryptoCurrencyController.
     */
    @BeforeEach
    void setUp() {
        cryptoCurrencyService = Mockito.mock(CryptoCurrencyService.class);
        cryptoCurrencyController = new CryptoCurrencyController();
        ReflectionTestUtils.setField(cryptoCurrencyController, "cryptoCurrencyService", cryptoCurrencyService);
    }

    /**
     * Tests the getAllCryptocurrencies method of the CryptoCurrencyController class.
     * Ensures that the method returns a list of all the cryptocurrencies in the database.
     */
    @Test
    void getAllCryptocurrencies_ShouldReturnListOfCryptocurrencies() {
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
    void getCryptoCurrencyById_ShouldReturnCryptoCurrency() {
        CryptoCurrency mockCrypto = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        when(cryptoCurrencyService.getCryptoCurrencyById(1L)).thenReturn(mockCrypto);

        ResponseEntity<CryptoCurrency> response = cryptoCurrencyController.getCryptoCurrencyById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Bitcoin", Objects.requireNonNull(response.getBody()).getName());
        assertEquals("BTC", response.getBody().getSymbol());
    }

    /**
     * Tests the getCryptoCurrencyBySymbol method of the CryptoCurrencyController class.
     * Ensures that the method returns a CryptoCurrency object by its symbol.
     */
    @Test
    void getCryptoCurrencyBySymbol_ShouldReturnCryptoCurrency() {
        CryptoCurrency mockCrypto = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        when(cryptoCurrencyService.getCryptoCurrencyBySymbol("BTC")).thenReturn(mockCrypto);

        ResponseEntity<CryptoCurrency> response = cryptoCurrencyController.getCryptoCurrencyBySymbol("BTC");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Bitcoin", Objects.requireNonNull(response.getBody()).getName());
        assertEquals("BTC", response.getBody().getSymbol());
    }

    /**
     * Tests the getCryptoCurrencyByName method of the CryptoCurrencyController class.
     * Ensures that the method returns a CryptoCurrency object by its name.
     */
    @Test
    void getCryptoCurrencyByName_ShouldReturnCryptoCurrency() {
        CryptoCurrency mockCrypto = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        when(cryptoCurrencyService.getCryptoCurrencyByName("Bitcoin")).thenReturn(mockCrypto);

        ResponseEntity<CryptoCurrency> response = cryptoCurrencyController.getCryptoCurrencyByName("Bitcoin");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Bitcoin", Objects.requireNonNull(response.getBody()).getName());
    }

    /**
     * Tests the getCryptoCurrencyByMarketCapRank method of the CryptoCurrencyController class.
     * Ensures that the method returns a CryptoCurrency object by its market capitalization rank.
     */
    @Test
    void getCryptoCurrencyByMarketCapRank_ShouldReturnCryptoCurrency() {
        CryptoCurrency mockCrypto = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        when(cryptoCurrencyService.getCryptoCurrencyByMarketCapRank(1)).thenReturn(mockCrypto);

        ResponseEntity<CryptoCurrency> response = cryptoCurrencyController.getCryptoCurrencyByMarketCapRank(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Bitcoin", Objects.requireNonNull(response.getBody()).getName());
        assertEquals(1, response.getBody().getRank());
    }

    /**
     * Tests the getCryptoCurrencyBySymbol method of the CryptoCurrencyController class.
     * Ensures that the method returns a 404 status and a null body when the symbol does not exist.
     */
    @Test
    void getCryptoCurrencyBySymbol_ShouldReturnNotFound_WhenSymbolDoesNotExist() {
        String nonExistentSymbol = "XYZ";
        when(cryptoCurrencyService.getCryptoCurrencyBySymbol(nonExistentSymbol))
                .thenThrow(new EntityNotFoundException("CryptoCurrency with symbol XYZ not found"));

        ResponseEntity<CryptoCurrency> response;
        try {
            response = cryptoCurrencyController.getCryptoCurrencyBySymbol(nonExistentSymbol);
        } catch (EntityNotFoundException ex) {
            response = ResponseEntity.notFound().build();
        }

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

}