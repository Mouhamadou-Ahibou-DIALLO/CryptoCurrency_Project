package com.cryptocurrency.data.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The CryptoCurrencyTest class is a JUnit test class for the CryptoCurrency class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class CryptoCurrencyTest {

    /**
     * The cryptoCurrency object to be tested.
     */
    private CryptoCurrency cryptoCurrency;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        cryptoCurrency = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
    }

    /**
     * Tests the constructor, getters, and setters of the CryptoCurrency class.
     */
    @Test
    public void testConstructor() {
        assertEquals(1L, cryptoCurrency.getId());
        assertEquals("Bitcoin", cryptoCurrency.getName());
        assertEquals("BTC", cryptoCurrency.getSymbol());
        assertEquals(1, cryptoCurrency.getMarketCapRank());
    }

    /**
     * Tests the getters of the CryptoCurrency class.
     * Ensures that the getters return the expected values.
     */
    @Test
    public void testGetters() {
        assertEquals(1L, cryptoCurrency.getId(), "The ID should be 1L");
        assertEquals("Bitcoin", cryptoCurrency.getName(), "The name should be 'Bitcoin'");
        assertEquals("BTC", cryptoCurrency.getSymbol(), "The symbol should be 'BTC'");
        assertEquals(1, cryptoCurrency.getMarketCapRank(), "The market cap rank should be 1");
    }

    /**
     * Tests the setters of the CryptoCurrency class.
     * Ensures that the setters correctly update the values of the object's properties.
     */
    @Test
    public void testSetters() {
        cryptoCurrency.setId(2L);
        cryptoCurrency.setName("Ethereum");
        cryptoCurrency.setSymbol("ETH");
        cryptoCurrency.setMarketCapRank(2);

        assertEquals(2L, cryptoCurrency.getId(), "The ID should be updated to 2L");
        assertEquals("Ethereum", cryptoCurrency.getName(), "The name should be updated to 'Ethereum'");
        assertEquals("ETH", cryptoCurrency.getSymbol(), "The symbol should be updated to 'ETH'");
        assertEquals(2, cryptoCurrency.getMarketCapRank(), "The market cap rank should be updated to 2");
    }
}
