package com.cryptocurrency.data.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The AlertsTest class is a JUnit test class for the Alerts class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class AlertsTest {

    /**
     * The alerts object is used to test the Alerts class.
     */
    private Alerts alerts;

    /**
     * The CryptoCurrency object is used to test the Alerts class.
     */
    private CryptoCurrency currency;

    /**
     * The user object is used to test the Alerts class.
     */
    private User user;

    /**
     * The setUp method is used to initialize the alerts, marketData and user objects
     * before each test.
     */
    @BeforeEach
    public void setUp() {
        currency = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        user = new User(1L, "user1",  "email1", "tokenHash1", "passwordHash1");
        alerts = new Alerts(1L, user, currency, 1.0, 1.0);
    }

    /**
     * The testConstructor method tests the constructor of the Alerts class.
     */
    @Test
    public void testConstructor() {
        assertEquals(1L, alerts.getId(), "The id of the alerts should be 1L");
        assertEquals(user, alerts.getUser(), "The user of the alerts should be the user object");
        assertEquals(currency, alerts.getCryptoCurrency(), "The market data of the alerts should be the marketData object");
        assertEquals(1.0, alerts.getPriceThreshold(), "The price threshold of the alerts should be 1.0");
        assertEquals(1.0, alerts.getVariationThreshold(), "The variation threshold of the alerts should be 1.0");
    }

    /**
     * The testGetters method tests the getters of the Alerts class.
     */
    @Test
    public void testGetters() {
        assertEquals(1L, alerts.getId(), "The id of the alerts should be 1L");
        assertEquals(user, alerts.getUser(), "The user of the alerts should be the user object");
        assertEquals(currency, alerts.getCryptoCurrency(), "The market data of the alerts should be the marketData object");
        assertEquals(1.0, alerts.getPriceThreshold(), "The price threshold of the alerts should be 1.0");
        assertEquals(1.0, alerts.getVariationThreshold(), "The variation threshold of the alerts should be 1.0");
    }

    /**
     * The testSetters method tests the setters of the Alerts class.
     */
    @Test
    public void testSetters() {
        alerts.setId(2L);
        alerts.setUser(user);
        alerts.setCryptoCurrency(currency);
        alerts.setPriceThreshold(2.0);
        alerts.setVariationThreshold(2.0);

        assertEquals(2L, alerts.getId(), "The id of the alerts should be 2L");
        assertEquals(user, alerts.getUser(), "The user of the alerts should be the user object");
        assertEquals(currency, alerts.getCryptoCurrency(), "The market data of the alerts should be the marketData object");
        assertEquals(2.0, alerts.getPriceThreshold(), "The price threshold of the alerts should be 2.0");
        assertEquals(2.0, alerts.getVariationThreshold(), "The variation threshold of the alerts should be 2.0");
    }
}
