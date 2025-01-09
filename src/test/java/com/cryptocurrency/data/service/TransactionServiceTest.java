package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.*;

import com.cryptocurrency.data.repository.CryptoCurrencyRepository;
import com.cryptocurrency.data.repository.TransactionRepository;
import com.cryptocurrency.data.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The TransactionServiceTest class is a JUnit test class for the TransactionService class.
 * Author: Mouhamadou Ahibou DIALLO
 */
class TransactionServiceTest {

    /**
     * The transactionService object is an instance of the TransactionService class.
     */
    @InjectMocks
    private TransactionService transactionService;

    /**
     * The userRepository, cryptoCurrencyRepository, transactionRepository, and emailService objects are mocks of the UserRepository, CryptoCurrencyRepository, TransactionRepository, and EmailService classes, respectively.
     */
    @Mock
    private UserRepository userRepository;

    /**
     * The cryptoCurrencyRepository and transactionRepository objects are mocks of the CryptoCurrencyRepository and TransactionRepository classes, respectively.
     */
    @Mock
    private CryptoCurrencyRepository cryptoCurrencyRepository;

    /**
     * The transactionRepository and emailService objects are mocks of the TransactionRepository and EmailService classes, respectively.
     */
    @Mock
    private TransactionRepository transactionRepository;

    /**
     * The emailService object is a mock of the EmailService class.
     */
    @Mock
    private EmailService emailService;

    /**
     * The user, cryptoCurrency, and transaction objects are instances of the User, CryptoCurrency, and Transaction classes, respectively.
     */
    private User user;
    private CryptoCurrency cryptoCurrency;
    private Transaction transaction;

    /**
     * Initializes the mock objects and sets up a user, cryptocurrency, and transaction
     * instance before each test. The user is assigned an ID and email, the cryptocurrency
     * is assigned an ID and price, and the transaction is assigned an ID, user, cryptocurrency,
     * amount invested, price at transaction, and quantity.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");

        cryptoCurrency = new CryptoCurrency();
        cryptoCurrency.setId(1L);
        cryptoCurrency.setPrice(50000.0);

        transaction = new Transaction();
        transaction.setId(1L);
        transaction.setUser(user);
        transaction.setCryptoCurrency(cryptoCurrency);
        transaction.setAmountInvested(1000.0);
        transaction.setPriceAtTransaction(50000.0);
        transaction.setQuantity(0.02);
    }

    /**
     * Verifies that the createTransaction method in TransactionService returns a Transaction
     * object created with the given user ID, cryptocurrency ID, and amount invested.
     * <p>
     * The method createTransaction is called with the user ID, cryptocurrency ID, and amount
     * invested, and it is verified that the method returns a Transaction object with the
     * given amount invested and quantity.
     * <p>
     * The method also verifies that the save method in TransactionRepository is called once
     * with the created Transaction object.
     */
    @Test
    void testCreateTransaction_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(cryptoCurrencyRepository.findById(1L)).thenReturn(Optional.of(cryptoCurrency));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction createdTransaction = transactionService.createTransaction(1L, 1L, 1000.0);

        assertNotNull(createdTransaction);
        assertEquals(1000.0, createdTransaction.getAmountInvested());
        assertEquals(0.02, createdTransaction.getQuantity());
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when attempting to create a transaction
     * with a non-existent user ID.
     * <p>
     * The method createTransaction is called with a user ID that does not exist in the repository,
     * and it is verified that an IllegalArgumentException with the appropriate error message is thrown.
     * </p>
     */
    @Test
    void testCreateTransaction_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                transactionService.createTransaction(1L, 1L, 1000.0)
        );

        assertEquals("L'utilisateur ou la crypto-monnaie n'existent pas.", exception.getMessage());
    }

    /**
     * Verifies that the getTransactions method in TransactionService returns a list of Transaction
     * objects when a user with the given ID exists.
     * <p>
     * The method getTransactions is called with a valid user ID, and it is verified that the method
     * returns a non-null list containing the expected number of Transaction objects.
     * <p>
     * The method also verifies that the findByUser method in TransactionRepository is called once
     * with the correct user object.
     */
    @Test
    void testGetTransactions_UserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(transactionRepository.findByUser(user)).thenReturn(List.of(transaction));

        List<Transaction> transactions = transactionService.getTransactions(1L);

        assertNotNull(transactions);
        assertEquals(1, transactions.size());
        verify(transactionRepository, times(1)).findByUser(user);
    }

    /**
     * Verifies that an IllegalArgumentException is thrown when attempting to retrieve the transactions
     * for a non-existent user ID.
     * <p>
     * The method getTransactions is called with a user ID that does not exist in the repository,
     * and it is verified that an IllegalArgumentException with the appropriate error message is thrown.
     * </p>
     */
    @Test
    void testGetTransactions_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                transactionService.getTransactions(1L)
        );

        assertEquals("L'utilisateur n'existe pas.", exception.getMessage());
    }

    /**
     * Verifies that the deleteTransaction method in TransactionService deletes the transaction
     * with the given ID successfully.
     * <p>
     * The method deleteTransaction is called with a valid transaction ID, and it is verified that
     * the method calls the delete method in TransactionRepository once with the correct transaction
     * object.
     * </p>
     */
    @Test
    void testDeleteTransaction_Success() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(cryptoCurrencyRepository.findById(1L)).thenReturn(Optional.of(cryptoCurrency));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        transactionService.deleteTransaction(1L);

        verify(transactionRepository, times(1)).delete(transaction);
    }

    /**
     * Verifies that an AssertionError is thrown when attempting to delete a transaction
     * with a non-existent ID.
     * <p>
     * The method deleteTransaction is called with a transaction ID that does not exist in the repository,
     * and it is verified that an AssertionError with an empty error message is thrown.
     * </p>
     */
    @Test
    void testDeleteTransaction_NotFound() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.empty());

        AssertionError exception = assertThrows(AssertionError.class, () ->
                transactionService.deleteTransaction(1L)
        );

        assertNull(exception.getMessage());
    }

    /**
     * Verifies that the updateTransaction method in TransactionService updates a transaction
     * with the given ID and new amount invested successfully.
     * <p>
     * The method updateTransaction is called with a valid transaction ID and a new amount
     * invested, and it is verified that the method returns a Transaction object with the
     * updated amount invested and quantity.
     * <p>
     * The method also verifies that the save method in TransactionRepository is called once
     * with the updated Transaction object.
     */
    @Test
    void testUpdateTransaction_Success() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(cryptoCurrencyRepository.findById(1L)).thenReturn(Optional.of(cryptoCurrency));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction updatedTransaction = transactionService.updateTransaction(1L, 2000.0);

        assertNotNull(updatedTransaction);
        assertEquals(2000.0, updatedTransaction.getAmountInvested());
        assertEquals(0.04, updatedTransaction.getQuantity());
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    /**
     * Verifies that the getPorfolioPerformance method in TransactionService returns a
     * PorfolioPerformance object with the total invested amount, total current value,
     * total gain or loss, and a list of CryptoPerformance objects when given a valid
     * user ID.
     * <p>
     * The method getPorfolioPerformance is called with a valid user ID, and it is
     * verified that the method returns a PorfolioPerformance object with the correct
     * total invested amount, total current value, total gain or loss, and a list of
     * CryptoPerformance objects.
     */
    @Test
    public void testGetPorfolioPerformance() {
        cryptoCurrency.setPrice(100.0);
        transaction.setAmountInvested(1000.0);
        transaction.setPriceAtTransaction(100.0);
        transaction.setQuantity(10.0);

        List<Transaction> transactions = List.of(transaction);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(transactionRepository.findByUser(user)).thenReturn(transactions);

        PorfolioPerformance performance = transactionService.getPorfolioPerformance(1L);

        assertEquals(1000.0, performance.getTotalInvested());
        assertEquals(1000.0, performance.getTotalCurrentValue());
        assertEquals(0.0, performance.getTotalGainOrLoss());
        assertEquals(1, performance.getCryptoPerformances().size());
    }

    /**
     * Verifies that the getPorfolioPerformance method in TransactionService returns a
     * PorfolioPerformance object with the total invested amount, total current value,
     * total gain or loss, and a list of CryptoPerformance objects when given a valid
     * user ID and there are no transactions for the user.
     * <p>
     * The method getPorfolioPerformance is called with a valid user ID and an empty
     * list of transactions for the user, and it is verified that the method returns a
     * PorfolioPerformance object with the total invested amount, total current value,
     * total gain or loss, and a list of CryptoPerformance objects.
     */
    @Test
    public void testGetPorfolioPerformance_EmptyTransactions() {
        cryptoCurrency.setPrice(100.0);
        transaction.setAmountInvested(1000.0);
        transaction.setPriceAtTransaction(100.0);
        transaction.setQuantity(10.0);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(transactionRepository.findByUser(user)).thenReturn(new ArrayList<>());

        PorfolioPerformance performance = transactionService.getPorfolioPerformance(1L);

        assertEquals(0.0, performance.getTotalInvested());
        assertEquals(0.0, performance.getTotalCurrentValue());
        assertEquals(0.0, performance.getTotalGainOrLoss());
        assertTrue(performance.getCryptoPerformances().isEmpty());
    }

    /**
     * Verifies that the checkAlert method in TransactionService sends an alert email
     * when the portfolio performance is greater than or equal to 10% of the amount
     * invested.
     * <p>
     * The method checkAlert is called with a list of transactions containing a
     * transaction with a gain of 20%, and it is verified that an alert email is
     * sent with the correct email address, subject, and message.
     */
    @Test
    public void testCheckAlert() {
        cryptoCurrency.setPrice(120.0);
        transaction.setAmountInvested(1000.0);
        transaction.setPriceAtTransaction(100.0);
        transaction.setQuantity(10.0);

        List<Transaction> transactions = List.of(transaction);
        when(transactionRepository.findAll()).thenReturn(transactions);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        transactionService.checkAlert();

        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> subjectCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);

        verify(emailService).sendEmail(emailCaptor.capture(), subjectCaptor.capture(), messageCaptor.capture());

        assertEquals("test@example.com", emailCaptor.getValue());
        assertEquals("Alerte : Performance de votre portefeuille", subjectCaptor.getValue());
        assertTrue(messageCaptor.getValue().contains("Bonne nouvelle"));
    }


    /**
     * Tests that no alert email is sent when the portfolio performance is not
     * greater than or equal to 10% of the amount invested.
     */
    @Test
    public void testCheckAlert_NoAlertSent() {
        cryptoCurrency.setPrice(100.0);
        transaction.setAmountInvested(1000.0);
        transaction.setPriceAtTransaction(100.0);
        transaction.setQuantity(10.0);

        transaction.setAmountInvested(1000.0);
        transaction.setQuantity(10.0);
        cryptoCurrency.setPrice(100.0);

        List<Transaction> transactions = List.of(transaction);
        when(transactionRepository.findAll()).thenReturn(transactions);

        transactionService.checkAlert();

        verify(emailService, never()).sendEmail(anyString(), anyString(), anyString());
    }
}
