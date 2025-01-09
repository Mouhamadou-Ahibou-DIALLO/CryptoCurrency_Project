package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.*;
import com.cryptocurrency.data.repository.CryptoCurrencyRepository;

import com.cryptocurrency.data.repository.TransactionRepository;
import com.cryptocurrency.data.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The TransactionService class provides methods for creating and retrieving transactions.
 * Author: Mouhamadou Ahibou DIALLO
 */
@Service
public class TransactionService {

    /**
     * The repository for Transaction objects.
     */
    @Autowired
    private CryptoCurrencyRepository cryptoCurrencyRepository;

    /**
     * The repository for User objects.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * The repository for Transaction objects.
     */
    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * The email service for sending emails.
     */
    @Autowired
    private EmailService emailService;

    /**
     * The sum of all gains or losses.
     */
    private static double sum = 0;

    /**
     * Default constructor.
     */
    public TransactionService() {}

    /**
     * Constructor with parameters.
     * @param cryptoCurrencyRepository The repository for CryptoCurrency objects.
     * @param userRepository The repository for User objects.
     * @param transactionRepository The repository for Transaction objects.
     * @param emailService The email service for sending emails.
     */
    public TransactionService(CryptoCurrencyRepository cryptoCurrencyRepository, UserRepository userRepository, TransactionRepository transactionRepository, EmailService emailService) {
        this.cryptoCurrencyRepository = cryptoCurrencyRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.emailService = emailService;
    }

    /**
     * Retrieves a list of transactions associated with a given user.
     *
     * @param userId The ID of the user whose transactions are to be retrieved.
     * @return A list of Transaction objects associated with the user.
     * @throws IllegalArgumentException if the user does not exist.
     */
    public List<Transaction> getTransactions(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("L'utilisateur n'existe pas.");
        }
        return transactionRepository.findByUser(user);
    }

    /**
     * Creates a new transaction for a given user and cryptocurrency.
     *
     * @param userId The ID of the user initiating the transaction.
     * @param cryptoId The ID of the cryptocurrency involved in the transaction.
     * @param amountInvested The amount of money invested in the transaction.
     * @return The created Transaction object.
     * @throws IllegalArgumentException If the user or cryptocurrency does not exist.
     */
    public Transaction createTransaction(Long userId, Long cryptoId, double amountInvested) {
        User user = userRepository.findById(userId).orElse(null);
        CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findById(cryptoId).orElse(null);

        if (user == null || cryptoCurrency == null) {
            throw new IllegalArgumentException("L'utilisateur ou la crypto-monnaie n'existent pas.");
        }

        double priceAtTransaction = cryptoCurrency.getPrice();
        double quantity = amountInvested / priceAtTransaction;
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setCryptoCurrency(cryptoCurrency);
        transaction.setAmountInvested(amountInvested);
        transaction.setPriceAtTransaction(priceAtTransaction);
        transaction.setQuantity(quantity);
        transaction.setTransactionDate(java.time.LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    /**
     * Updates a transaction with the given ID with the given amount invested.
     *
     * @param transactionId The ID of the transaction to be updated.
     * @param amountInvested The new amount invested.
     * @return The updated Transaction object.
     * @throws IllegalArgumentException if the transaction does not exist or if the user or cryptocurrency associated with the
     * transaction does not exist.
     */
    public Transaction updateTransaction(Long transactionId, double amountInvested) {
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);
        if (transaction == null) {
            throw new IllegalArgumentException("La transaction n'existe pas.");
        }

        User user = userRepository.findById(transaction.getUser().getId()).orElse(null);
        CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findById(transaction.getCryptoCurrency().getId()).orElse(null);

        if (user == null || cryptoCurrency == null) {
            throw new IllegalArgumentException("L'utilisateur ou la crypto-monnaie n'existent pas.");
        }

        double quantity = amountInvested / transaction.getPriceAtTransaction();
        transaction.setQuantity(quantity);
        transaction.setAmountInvested(amountInvested);
        return transactionRepository.save(transaction);
    }

    /**
     * Deletes a transaction with the given ID.
     *
     * @param transactionId The ID of the transaction to be deleted.
     * @throws IllegalArgumentException if the transaction does not exist or if the user or cryptocurrency associated with the
     * transaction does not exist.
     */
    public void deleteTransaction(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);
        assert transaction != null;

        User user = userRepository.findById(transaction.getUser().getId()).orElse(null);
        CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findById(transaction.getCryptoCurrency().getId()).orElse(null);

        if (user == null || cryptoCurrency == null) {
            throw new IllegalArgumentException("L'utilisateur ou la crypto-monnaie n'existent pas.");
        }

        transactionRepository.delete(transaction);
    }

    /**
     * Calculates and retrieves the performance of a user's portfolio.
     *
     * @param userId The ID of the user whose portfolio performance is to be calculated.
     * @return A PorfolioPerformance object containing the total invested amount,
     *         total current value, total gain or loss, and a list of CryptoPerformance
     *         objects for each cryptocurrency in the portfolio.
     * @throws IllegalArgumentException if any cryptocurrency in the user's transactions does not exist.
     */
    public PorfolioPerformance getPorfolioPerformance(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        assert user != null;
        List<Transaction> transactions = transactionRepository.findByUser(user);

        if (transactions.isEmpty()) {
            return new PorfolioPerformance(0, 0, 0, new ArrayList<>());
        }

        double totalInvested = 0;
        double totalCurrentValue = 0;
        List<CryptoPerformance> cryptoPerformances = new ArrayList<>();

        for (Transaction transaction : transactions) {
            double priceAtTransaction = transaction.getPriceAtTransaction();
            double amountInvested = transaction.getAmountInvested();
            double quantity = amountInvested / priceAtTransaction;

            CryptoCurrency cryptoCurrency = transaction.getCryptoCurrency();
            if (cryptoCurrency == null) {
                throw new IllegalArgumentException("La crypto-monnaie n'existe pas.");
            }

            double currentPrice =cryptoCurrency.getPrice();
            double currentValue = quantity * currentPrice;
            double gainOrLoss = currentValue - amountInvested;

            totalInvested += amountInvested;
            totalCurrentValue += currentValue;

            cryptoPerformances.add(new CryptoPerformance(
                    transaction.getCryptoCurrency().getId(),
                    amountInvested,
                    currentValue,
                    gainOrLoss
            ));
        }

        double totalGainOrLoss = totalCurrentValue - totalInvested;
        System.out.println("know portfolio performance: " + totalGainOrLoss);

        return new PorfolioPerformance(totalInvested, totalCurrentValue, totalGainOrLoss, cryptoPerformances);
    }

    /**
     * Check the performance of the user's portfolio and send an alert email if it is different from the last check.
     * @param userId The ID of the user.
     * @param totalGainOrLoss The total gain or loss of the portfolio.
     */
    private void checkPerformance(Long userId, double totalGainOrLoss) {
        User user = userRepository.findById(userId).orElse(null);
        assert user != null;
        String subject = "Alerte : Performance de votre portefeuille";
        String message = totalGainOrLoss > 0 ?
                String.format("Bonne nouvelle ! Vous avez réalisé un gain de %.2f € sur votre portefeuille.", totalGainOrLoss) :
                String.format("Attention ! Vous avez une perte de %.2f € sur votre portefeuille.", totalGainOrLoss);

        String email = user.getEmail();
        sum = sum + totalGainOrLoss;
        emailService.sendEmail(email, subject, message);
    }

    /**
     * Check the performance of each user's portfolio and send an alert email if it is different from the last check.
     * <p>
     * This method is used to check the performance of each user's portfolio by iterating through all transactions.
     * For each transaction, it calculate the total gain or loss and check if it is greater than or equal to
     * 10% of the amount invested. If it is, it calls the checkPerformance method to send an alert email.
     */
    public void checkAlert() {
        List<Transaction> transactions = transactionRepository.findAll();
        for (Transaction transaction : transactions) {
            User user = transaction.getUser();
            double totalGainOrLoss = transaction.getCryptoCurrency().getPrice() * transaction.getQuantity() - transaction.getAmountInvested();
            if (Math.abs(totalGainOrLoss) >= transaction.getAmountInvested() * 0.1) {
                checkPerformance(user.getId(), totalGainOrLoss);
            }
        }
    }
}
