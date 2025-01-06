package com.cryptocurrency.data.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The PorfolioPerformanceTest class is a JUnit test class for the PorfolioPerformance class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class PorfolioPerformanceTest {

    /**
     * The porfolioPerformance object to be tested.
     */
    private PorfolioPerformance porfolioPerformance;

    /**
     * Sets up the test environment by creating a new PorfolioPerformance object.
     */
    @BeforeEach
    public void setUp() {
        List<CryptoPerformance> cryptoPerformances = new ArrayList<>();
        double totalInvested = 100.0;
        double totalCurrentValue = 200.0;
        double totalGainOrLoss = 100.0;
        porfolioPerformance= new PorfolioPerformance(totalInvested, totalCurrentValue, totalGainOrLoss, cryptoPerformances);
    }

    /**
     * The testGetteursAndSetteurs method tests the getters and setters of the PorfolioPerformance class.
     * Ensures that the getters and setters correctly update the values of the object's properties.
     */
    @Test
    public void testGetteursAndSetteurs() {
        assertEquals(100.0, porfolioPerformance.getTotalInvested());
        assertEquals(200.0, porfolioPerformance.getTotalCurrentValue());
        assertEquals(100.0, porfolioPerformance.getTotalGainOrLoss());

        List<CryptoPerformance> cryptoPerformances = new ArrayList<>();
        porfolioPerformance.setCryptoPerformances(cryptoPerformances);
        assertEquals(cryptoPerformances, porfolioPerformance.getCryptoPerformances());

        porfolioPerformance.setTotalCurrentValue(300.0);
        assertEquals(300.0, porfolioPerformance.getTotalCurrentValue());

        porfolioPerformance.setTotalGainOrLoss(200.0);
        assertEquals(200.0, porfolioPerformance.getTotalGainOrLoss());

        porfolioPerformance.setTotalInvested(200.0);
        assertEquals(200.0, porfolioPerformance.getTotalInvested());
    }
}
