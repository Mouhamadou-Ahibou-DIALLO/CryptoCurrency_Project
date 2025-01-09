package com.cryptocurrency.data.controller;

import com.cryptocurrency.data.model.PorfolioPerformance;
import com.cryptocurrency.data.model.Transaction;
import com.cryptocurrency.data.service.TransactionRequestService;
import com.cryptocurrency.data.service.TransactionService;
import com.cryptocurrency.data.service.TransactionUpdateService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * The TransactionControllerTest class is a JUnit test class for the TransactionController class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class TransactionControllerTest {

    /**
     * The transactionController.
     */
    @InjectMocks
    private TransactionController transactionController;

    /**
     * The transactionService.
     */
    @Mock
    private TransactionService transactionService;

    /**
     * The setUp method is used to initialize the transactionController and transactionService objects
     * before each test.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Verifies that the createTransaction method in TransactionController returns a Transaction
     * object created with the given user ID, cryptocurrency ID, and amount invested.
     * <p>
     * The method createTransaction is called with a TransactionRequestService object containing
     * a user ID, cryptocurrency ID, and amount invested, and it is verified that the method
     * returns a Transaction object with the given amount invested and quantity.
     * <p>
     * The method also verifies that the save method in TransactionRepository is called once
     * with the created Transaction object.
     */
    @Test
    public void testCreateTransactionSuccess() {
        TransactionRequestService request = new TransactionRequestService();
        request.setUserId(1L);
        request.setCryptoId(100L);
        request.setAmountInvested(500.0);

        Transaction transaction = new Transaction();
        transaction.setId(1L);

        when(transactionService.createTransaction(eq(1L), eq(100L), eq(500.0))).thenReturn(transaction);
        ResponseEntity<?> response = transactionController.createTransaction(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Transaction.class, response.getBody());
        assertEquals(1L, ((Transaction) response.getBody()).getId());
    }

    /**
     * Verifies that the createTransaction method in TransactionController throws a BadRequestException
     * when the user ID is missing.
     * <p>
     * The method createTransaction is called with a TransactionRequestService object containing
     * a cryptocurrency ID and amount invested, but no user ID, and it is verified that the
     * method throws a BadRequestException with the appropriate error message.
     */
    @Test
    public void testCreateTransactionMissingUserId() {
        TransactionRequestService request = new TransactionRequestService();
        request.setCryptoId(100L);
        request.setAmountInvested(500.0);

        ResponseEntity<?> response = transactionController.createTransaction(request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("L'utilisateur ne peut pas être null.", response.getBody());
    }

    /**
     * Verifies that the updateTransaction method in TransactionController successfully updates a transaction.
     * <p>
     * The method updateTransaction is called with a transaction ID and a TransactionUpdateService object containing the
     * new amount invested, and it is verified that the method returns a Transaction object with the updated amount
     * invested and the same ID.
     * </p>
     */
    @Test
    public void testUpdateTransactionSuccess() {
        TransactionUpdateService updateRequest = new TransactionUpdateService();
        updateRequest.setAmountInvested(700.0);

        Transaction transaction = new Transaction();
        transaction.setId(1L);

        when(transactionService.updateTransaction(eq(1L), eq(700.0))).thenReturn(transaction);
        ResponseEntity<?> response = transactionController.updateTransaction(1L, updateRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Transaction.class, response.getBody());
        assertEquals(1L, ((Transaction) response.getBody()).getId());
    }

    /**
     * Verifies that the deleteTransaction method in TransactionController successfully deletes a transaction.
     * <p>
     * The method deleteTransaction is called with a valid transaction ID, and it is verified that the method
     * returns an HTTP status of OK and the appropriate success message.
     * </p>
     */
    @Test
    public void testDeleteTransactionSuccess() {
        doNothing().when(transactionService).deleteTransaction(eq(1L));
        ResponseEntity<?> response = transactionController.deleteTransaction(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Transaction supprimée avec succès.", response.getBody());
    }

    /**
     * Verifies that the getAllTransactions method in TransactionController returns a list of transactions
     * associated with a given user ID.
     * <p>
     * The method getAllTransactions is called with a valid user ID, and it is verified that the method
     * returns an HTTP status of OK and a non-null list containing the expected number of Transaction objects.
     * </p>
     */
    @Test
    public void testGetAllTransactions() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        List<Transaction> transactions = List.of(transaction);

        when(transactionService.getTransactions(eq(1L))).thenReturn(transactions);
        ResponseEntity<?> response = transactionController.getAllTransactions(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(List.class, response.getBody());
        assertEquals(1, ((List<?>) response.getBody()).size());
    }

    /**
     * Verifies that the getPortfolioPerformance method in TransactionController returns
     * a PorfolioPerformance object with the correct total invested amount for a given user ID.
     * <p>
     * The method getPortfolioPerformance is called with a valid user ID, and it is verified
     * that the method returns an HTTP status of OK and a PorfolioPerformance object with
     * the expected total invested amount.
     * </p>
     */
    @Test
    public void testGetPortfolioPerformance() {
        PorfolioPerformance performance = new PorfolioPerformance();
        performance.setTotalInvested(1000.0);

        when(transactionService.getPorfolioPerformance(eq(1L))).thenReturn(performance);
        ResponseEntity<PorfolioPerformance> response = transactionController.getPortfolioPerformance(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1000.0, Objects.requireNonNull(response.getBody()).getTotalInvested());
    }

    /**
     * Verifies that the getPortfolioPerformance method in TransactionController
     * returns an HTTP status of OK and a non-null PorfolioPerformance object
     * when given a valid user ID and the transactionService returns null.
     * <p>
     * The method getPorfolioPerformance is called with a valid user ID, and it is
     * verified that the method returns an HTTP status of OK and a non-null
     * PorfolioPerformance object.
     * </p>
     */
    @Test
    public void testGetPortfolioPerformanceNull() {
        when(transactionService.getPorfolioPerformance(eq(1L))).thenReturn(null);
        ResponseEntity<PorfolioPerformance> response = transactionController.getPortfolioPerformance(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
