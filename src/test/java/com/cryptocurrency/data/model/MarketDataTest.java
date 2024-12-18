package com.cryptocurrency.data.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The MarketDataTest class is a JUnit test class for the MarketData class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class MarketDataTest {

    /**
     * The marketData object is used to test the MarketData class.
     */
    private MarketData marketData;

    /**
     * The cryptoCurrency object is used to test the MarketData class.
     */
    private CryptoCurrency cryptoCurrency;

    /**
     * The cryptoCurrency2 object is used to test the MarketData class.
     */
    private CryptoCurrency cryptoCurrency2;

    /**
     * Sets up the test environment before each test.
     * Creates a MarketData object, a CryptoCurrency object and a CryptoCurrency2 object.
     */
    @BeforeEach
    public void setUp() {
        cryptoCurrency2 = new CryptoCurrency(2L, "Bitcoin Cash", "BTC", 10);
        cryptoCurrency = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        marketData = new MarketData(1L, cryptoCurrency, LocalDateTime.now(), 1.0, 1.0, 1.0);
    }

    /**
     * Tests the constructor of the MarketData class.
     * Ensures that the constructor initializes the fields correctly.
     */
    @Test
    public void testConstructor() {
        assertEquals(1L, marketData.getId(), "The ID should be 1L");
        assertEquals(1.0, marketData.getPriceUsd(), "The price in USD should be 1.0");
        assertEquals(1.0, marketData.getVolumeUsd(), "The volume in USD should be 1.0");
        assertEquals(1.0, marketData.getMarketCapUsd(), "The market cap in USD should be 1.0");
        assertEquals(cryptoCurrency, marketData.getCryptoCurrency(), "The associated CryptoCurrency should be 'cryptoCurrency'");
    }

    /**
     * Tests the getters of the MarketData class.
     * Ensures that the getters return the expected values.
     */
    @Test
    public void testGetters() {
        assertEquals(1L, marketData.getId(), "The ID should be 1L");
        assertEquals(1.0, marketData.getPriceUsd(), "The price in USD should be 1.0");
        assertEquals(1.0, marketData.getVolumeUsd(), "The volume in USD should be 1.0");
        assertEquals(1.0, marketData.getMarketCapUsd(), "The market cap in USD should be 1.0");
        assertEquals(cryptoCurrency, marketData.getCryptoCurrency(), "The associated CryptoCurrency should be 'cryptoCurrency'");
    }

    /**
     * Tests the setters of the MarketData class.
     * Ensures that the setters update the fields correctly.
     */
    @Test
    public void testSetters() {
        marketData.setId(2L);
        marketData.setPriceUsd(2.0);
        marketData.setVolumeUsd(2.0);
        marketData.setMarketCapUsd(2.0);
        marketData.setCryptoCurrency(cryptoCurrency2);

        assertEquals(2L, marketData.getId(), "The ID should be 2L");
        assertEquals(2.0, marketData.getPriceUsd(), "The price in USD should be 2.0");
        assertEquals(2.0, marketData.getVolumeUsd(), "The volume in USD should be 2.0");
        assertEquals(2.0, marketData.getMarketCapUsd(), "The market cap in USD should be 2.0");
        assertEquals(cryptoCurrency2, marketData.getCryptoCurrency(), "The associated CryptoCurrency should be 'cryptoCurrency2'");
    }
}
