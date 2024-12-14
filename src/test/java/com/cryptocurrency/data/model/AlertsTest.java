package com.cryptocurrency.data.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlertsTest {

    private Alerts alerts;
    private MarketData marketData;
    private User user;

    @BeforeEach
    public void setUp() {
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        marketData = new MarketData(1L, cryptoCurrency, LocalDateTime.now(), 1.0, 1.0, 1.0);
        user = new User(1L, "user1",  "email1", "tokenHash1", "passwordHash1");
        alerts = new Alerts(1L, user, marketData, 1.0, 1.0);
    }

    @Test
    public void testConstructor() {
        assertEquals(1L, alerts.getId());
        assertEquals(user, alerts.getUser());
        assertEquals(marketData, alerts.getMarketData());
        assertEquals(1.0, alerts.getPriceThreshold());
        assertEquals(1.0, alerts.getVariationThreshold());
    }

    @Test
    public void testGetters() {
        assertEquals(1L, alerts.getId());
        assertEquals(user, alerts.getUser());
        assertEquals(marketData, alerts.getMarketData());
        assertEquals(1.0, alerts.getPriceThreshold());
        assertEquals(1.0, alerts.getVariationThreshold());
    }

    @Test
    public void testSetters() {
        alerts.setId(2L);
        alerts.setUser(user);
        alerts.setMarketData(marketData);
        alerts.setPriceThreshold(2.0);
        alerts.setVariationThreshold(2.0);

        assertEquals(2L, alerts.getId());
        assertEquals(user, alerts.getUser());
        assertEquals(marketData, alerts.getMarketData());
        assertEquals(2.0, alerts.getPriceThreshold());
        assertEquals(2.0, alerts.getVariationThreshold());
    }
}
