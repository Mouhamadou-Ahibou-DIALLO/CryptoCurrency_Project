package com.cryptocurrency.data.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The TransactionRequestServiceTest class is a JUnit test class for the TransactionRequestService class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class TransactionRequestServiceTest {

    /**
     * Tests the getters and setters of the TransactionRequestService class.
     * Verifies that the setters correctly update the values of the object's properties.
     * Verifies that the getters return the same values that were set.
     */
    @Test
    public void testGetteursAbdSetteurs() {
        TransactionRequestService transactionRequestService = new TransactionRequestService();
        transactionRequestService.setUserId(1L);
        transactionRequestService.setCryptoId(1L);
        transactionRequestService.setAmountInvested(1.0);

        assertEquals(1L, (long) transactionRequestService.getUserId());
        assertEquals(1L, (long) transactionRequestService.getCryptoId());
        assertEquals(1.0, transactionRequestService.getAmountInvested());
    }
}
