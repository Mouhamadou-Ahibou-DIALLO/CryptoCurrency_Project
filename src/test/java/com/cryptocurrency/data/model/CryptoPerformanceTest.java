package com.cryptocurrency.data.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The CryptoPerformanceTest class is a JUnit test class for the CryptoPerformance class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class CryptoPerformanceTest {

    /**
     * The CryptoPerformance object to test.
     */
    private CryptoPerformance cryptoPerformance;

    /**
     * The setUp method is used to initialize the cryptoPerformance object before each test.
     * The values set are 1L for the cryptoId, 1.0 for the investedAmount, 1.0 for the currentValue, and 1.0 for the gainOrLoss.
     */
    @BeforeEach
    public void setUp() {
        cryptoPerformance = new CryptoPerformance(1L, 1.0, 1.0, 1.0);
    }

    /**
     * The testConstructor method tests the constructor of the CryptoPerformance class.
     * Ensures that the constructor correctly initializes the object's properties.
     */
    @Test
    public void testConstructor() {
        assertEquals(1L, cryptoPerformance.getCryptoId());
        assertEquals(1.0, cryptoPerformance.getCurrentValue());
        assertEquals(1.0, cryptoPerformance.getInvestedAmount());
        assertEquals(1.0, cryptoPerformance.getGainOrLoss());
    }

    /**
     * The testGettersAndSetters method tests the getters and setters of the CryptoPerformance class.
     * Ensures that the getters and setters correctly update the values of the object's properties.
     */
    @Test
    public void testGettersAndSetters() {
        cryptoPerformance.setCryptoId(1L);
        cryptoPerformance.setCurrentValue(1.0);
        cryptoPerformance.setInvestedAmount(1.0);
        cryptoPerformance.setGainOrLoss(1.0);

        assertEquals(1L, cryptoPerformance.getCryptoId());
        assertEquals(1.0, cryptoPerformance.getCurrentValue());
        assertEquals(1.0, cryptoPerformance.getInvestedAmount());
        assertEquals(1.0, cryptoPerformance.getGainOrLoss());
    }
}
