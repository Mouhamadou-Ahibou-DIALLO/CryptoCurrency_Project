package com.cryptocurrency.data.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

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
     * The cryptoCurrency2 object to be tested.
     */
    private CryptoCurrency cryptoCurrency2;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        cryptoCurrency = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        cryptoCurrency2 = new CryptoCurrency(2L, "Ethereum", "ETH", 2, 2.,
                1., 1., 1., 1., 1., 1., LocalDateTime.now());
    }

    /**
     * Tests the constructor, getters, and setters of the CryptoCurrency class.
     */
    @Test
    public void testConstructor() {
        assertEquals(1L, cryptoCurrency.getId());
        assertEquals("Bitcoin", cryptoCurrency.getName());
        assertEquals("BTC", cryptoCurrency.getSymbol());
        assertEquals(1, cryptoCurrency.getRank());

        assertEquals(2, cryptoCurrency2.getSupply());
        assertEquals(1., cryptoCurrency2.getMaxSupply());
        assertEquals(1., cryptoCurrency2.getMarket());
        assertEquals(1., cryptoCurrency2.getVolume());
        assertEquals(1., cryptoCurrency2.getPrice());
        assertEquals(1., cryptoCurrency2.getChange());
        assertEquals(1., cryptoCurrency2.getVwap());
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
        assertEquals(1, cryptoCurrency.getRank(), "The market cap rank should be 1");

        assertEquals(2, cryptoCurrency2.getSupply());
        assertEquals(1., cryptoCurrency2.getMaxSupply());
        assertEquals(1., cryptoCurrency2.getMarket());
        assertEquals(1., cryptoCurrency2.getVolume());
        assertEquals(1., cryptoCurrency2.getPrice());
        assertEquals(1., cryptoCurrency2.getChange());
        assertEquals(1., cryptoCurrency2.getVwap());
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
        cryptoCurrency.setRank(2);
        String date = "2022-01-01T00:00:00";
        cryptoCurrency.setTimestamp(LocalDateTime.parse(date));

        assertEquals(2022, cryptoCurrency.getTimestamp().getYear(), "The year should be 2022");
        assertEquals(2L, cryptoCurrency.getId(), "The ID should be updated to 2L");
        assertEquals("Ethereum", cryptoCurrency.getName(), "The name should be updated to 'Ethereum'");
        assertEquals("ETH", cryptoCurrency.getSymbol(), "The symbol should be updated to 'ETH'");
        assertEquals(2, cryptoCurrency.getRank(), "The market cap rank should be updated to 2");

        cryptoCurrency2.setSupply(3.);
        cryptoCurrency2.setMaxSupply(3.);
        cryptoCurrency2.setMarket(3.);
        cryptoCurrency2.setVolume(3.);
        cryptoCurrency2.setPrice(3.);
        cryptoCurrency2.setChange(3.);
        cryptoCurrency2.setVwap(3.);

        assertEquals(3., cryptoCurrency2.getSupply());
        assertEquals(3., cryptoCurrency2.getMaxSupply());
        assertEquals(3., cryptoCurrency2.getMarket());
        assertEquals(3., cryptoCurrency2.getVolume());
        assertEquals(3., cryptoCurrency2.getPrice());
        assertEquals(3., cryptoCurrency2.getChange());
        assertEquals(3., cryptoCurrency2.getVwap());
    }
}
