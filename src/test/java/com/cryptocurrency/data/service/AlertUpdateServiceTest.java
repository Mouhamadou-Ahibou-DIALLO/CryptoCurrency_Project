package com.cryptocurrency.data.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The AlertUpdateServiceTest class is a JUnit test class for the AlertUpdateService class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class AlertUpdateServiceTest {

    /**
     * The alertUpdateService object is used to test the AlertUpdateService class.
     */
    private AlertUpdateService alertUpdateService;

    /**
     * The setUp() method is used to initialize the alertUpdateService object
     * before each test.
     */
    @BeforeEach
    public void setUp() {
        alertUpdateService = new AlertUpdateService();
    }

    /**
     * The testGetteursAndSetteurs() method tests the getters and setters of the
     * AlertUpdateService class. This test verifies that the getters and setters
     * of the AlertUpdateService class are working correctly by setting values
     * and verifying that the getters return the set values.
     */
    @Test
    public void testGetteursAndSetteurs() {
        alertUpdateService.setName("Test");
        assertEquals("Test", alertUpdateService.getName());

        alertUpdateService.setPriceThreshold(10.0);
        assertEquals(10.0, alertUpdateService.getPriceThreshold());

        alertUpdateService.setVariationThreshold(5.0);
        assertEquals(5.0, alertUpdateService.getVariationThreshold());
    }
}
