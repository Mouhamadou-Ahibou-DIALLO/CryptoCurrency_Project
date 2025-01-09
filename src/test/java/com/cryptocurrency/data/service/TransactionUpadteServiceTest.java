package com.cryptocurrency.data.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The TransactionUpadteServiceTest class is a JUnit test class for the TransactionUpadteService class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class TransactionUpadteServiceTest {

    /**
     * Tests the getter and setter for the amountInvested property of the
     * TransactionUpdateService class. Verifies that the setter updates the
     * amountInvested value correctly and the getter retrieves the same value.
     */
     @Test
    public void testGetterAndSetter() {
        TransactionUpdateService transactionUpadteService = new TransactionUpdateService();
        transactionUpadteService.setAmountInvested(100.0);
        assertEquals(100.0, transactionUpadteService.getAmountInvested());
    }
}
