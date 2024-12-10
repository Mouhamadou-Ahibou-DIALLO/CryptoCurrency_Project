package com.cryptocurrency.data.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarketDataTest {

    private MarketData marketData;
    private CryptoCurrency cryptoCurrency;
    private CryptoCurrency cryptoCurrency2;

    @BeforeEach
    public void setUp() {
        cryptoCurrency2 = new CryptoCurrency(2L, "Bitcoin Cash", "BTC", 10);
        cryptoCurrency = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        marketData = new MarketData(1L, cryptoCurrency, LocalDateTime.now(), 1.0, 1.0, 1.0);
    }

    @Test
    public void testConstructor() {
        assertEquals(1L, marketData.getId());
        assertEquals(1.0, marketData.getPriceUsd());
        assertEquals(1.0, marketData.getVolumeUsd());
        assertEquals(1.0, marketData.getMarketCapUsd());
        assertEquals(cryptoCurrency, marketData.getCryptoCurrency());
    }

    @Test
    public void testGetters() {
        assertEquals(1L, marketData.getId());
        assertEquals(1.0, marketData.getPriceUsd());
        assertEquals(1.0, marketData.getVolumeUsd());
        assertEquals(1.0, marketData.getMarketCapUsd());
        assertEquals(cryptoCurrency, marketData.getCryptoCurrency());
    }

    @Test
    public void testSetters() {
        marketData.setId(2L);
        marketData.setPriceUsd(2.0);
        marketData.setVolumeUsd(2.0);
        marketData.setMarketCapUsd(2.0);
        marketData.setCryptoCurrency(cryptoCurrency2);

        assertEquals(2L, marketData.getId());
        assertEquals(2.0, marketData.getPriceUsd());
        assertEquals(2.0, marketData.getVolumeUsd());
        assertEquals(2.0, marketData.getMarketCapUsd());
        assertEquals(cryptoCurrency2, marketData.getCryptoCurrency());
    }
}
