package com.cryptocurrency.data.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CryptoCurrencyTest {

    private CryptoCurrency cryptoCurrency;

    @BeforeEach
    public void setUp() {
        cryptoCurrency = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
    }

    @Test
    public void testConstructor() {
        assertEquals(1L, cryptoCurrency.getId());
        assertEquals("Bitcoin", cryptoCurrency.getName());
        assertEquals("BTC", cryptoCurrency.getSymbol());
        assertEquals(1, cryptoCurrency.getMarketCapRank());
    }

    @Test
    public void testGetters() {
        assertEquals(1L, cryptoCurrency.getId());
        assertEquals("Bitcoin", cryptoCurrency.getName());
        assertEquals("BTC", cryptoCurrency.getSymbol());
        assertEquals(1, cryptoCurrency.getMarketCapRank());
    }

    @Test
    public void testSetters() {
        cryptoCurrency.setId(2L);
        cryptoCurrency.setName("Ethereum");
        cryptoCurrency.setSymbol("ETH");
        cryptoCurrency.setMarketCapRank(2);

        assertEquals(2L, cryptoCurrency.getId());
        assertEquals("Ethereum", cryptoCurrency.getName());
        assertEquals("ETH", cryptoCurrency.getSymbol());
        assertEquals(2, cryptoCurrency.getMarketCapRank());
    }
}
