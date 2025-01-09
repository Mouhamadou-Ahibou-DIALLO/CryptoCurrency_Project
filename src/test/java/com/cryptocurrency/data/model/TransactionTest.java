package com.cryptocurrency.data.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The TransactionTest class is a JUnit test class for the Transaction class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class TransactionTest {

    /**
     * The transaction object to test.
     */
    private Transaction transaction;

    /**
     * Initializes the transaction object to test.
     * The transaction object is created with valid values before each test.
     */
    @BeforeEach
    public void setUp() {
        User user = new User(1L, "username", "email", "tokenHash", "passwordHash");
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "name", "symbol", 10);
        LocalDateTime now = LocalDateTime.now();
        transaction = new Transaction(1L, user, cryptoCurrency, 100.0, 10.0, 1.0, now);
    }

    /**
     * Tests the getters and setters of the Transaction class.
     * Verifies that the initial values of the transaction's properties are correct.
     * Updates the transaction's properties using setters and confirms the updated values
     * using getters.
     */
    @Test
    public void testGetteursAndSetteurs() {
        assertEquals(1L, transaction.getId());
        assertEquals(1L, transaction.getUser().getId());
        assertEquals(1L, transaction.getCryptoCurrency().getId());
        assertEquals(100.0, transaction.getAmountInvested());
        assertEquals(10.0, transaction.getPriceAtTransaction());
        assertEquals(1.0, transaction.getQuantity());

        transaction.setId(2L);
        transaction.getUser().setId(2L);
        transaction.getCryptoCurrency().setId(2L);
        transaction.setAmountInvested(200.0);
        transaction.setPriceAtTransaction(20.0);
        transaction.setQuantity(2.0);
        transaction.setTransactionDate(LocalDateTime.now());
        System.out.println(transaction.getTransactionDate());

        assertEquals(2L, transaction.getId());
        assertEquals(2L, transaction.getUser().getId());
        assertEquals(2L, transaction.getCryptoCurrency().getId());
        assertEquals(200.0, transaction.getAmountInvested());
        assertEquals(20.0, transaction.getPriceAtTransaction());
        assertEquals(2.0, transaction.getQuantity());
    }
}
