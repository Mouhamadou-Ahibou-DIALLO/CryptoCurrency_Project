package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The AlertCreateServiceTest class is a JUnit test class for the AlertCreateService class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class AlertCreateServiceTest {

    /**
     * The user object is created and initialized with the values of the user object.
     */
    private User user;

    /**
     * The cryptoCurrency object is created and initialized with the values of the cryptoCurrency object.
     */
    private CryptoCurrency cryptoCurrency;

    /**
     * The alertCreatedService object is created and initialized with the values of the alertCreatedService object.
     */
    private AlertCreatedService alertCreatedService;

    /**
     * Sets up the test environment before each test.
     * Initializes the user, cryptoCurrency, and alertCreatedService objects
     * with predefined values.
     */
    @BeforeEach
    public void setUp() {
        user = new User(1L, "user1", "email1", "tokenHash1", "passwordHash1");
        cryptoCurrency = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        alertCreatedService = new AlertCreatedService();
    }

    /**
     * Test the getters and setters of the AlertCreatedService class.
     * This test verifies that the getters and setters of the AlertCreatedService class
     * are working correctly by setting values and verifying that the getters return the set values.
     */
    @Test
    public void testGetteursAndSetteurs() {
        alertCreatedService.setUser(user);
        alertCreatedService.setCryptoCurrency(cryptoCurrency);
        alertCreatedService.setName("alert1");
        alertCreatedService.setPriceThreshold(100.0);
        alertCreatedService.setVariationThreshold(1.0);

        assert alertCreatedService.getUser().equals(user);
        assert alertCreatedService.getCryptoCurrency().equals(cryptoCurrency);
        assert alertCreatedService.getName().equals("alert1");
        assert alertCreatedService.getPriceThreshold().equals(100.0);
        assert alertCreatedService.getVariationThreshold().equals(1.0);
    }
}
