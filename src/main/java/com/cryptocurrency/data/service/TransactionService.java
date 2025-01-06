package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.*;
import com.cryptocurrency.data.repository.CryptoCurrencyRepository;

import com.cryptocurrency.data.repository.TransactionRepository;
import com.cryptocurrency.data.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private CryptoCurrencyRepository cryptoCurrencyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private EmailService emailService;

    public List<Transaction> getTransactions(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("L'utilisateur n'existe pas.");
        }
        return transactionRepository.findByUser(user);
    }

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

    public PorfolioPerformance getPorfolioPerformance(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        assert user != null;
        System.out.println("user: " + user.getUsername());
        List<Transaction> transactions = transactionRepository.findByUser(user);

        if (transactions.isEmpty()) {
            return new PorfolioPerformance(0, 0, 0, new ArrayList<>());
        }

        double totalInvested = 0;
        double totalCurrentValue = 0;
        List<CryptoPerformance> cryptoPerformances = new ArrayList<>();

        for (Transaction transaction : transactions) {
            System.out.println("transaction: " + transaction);
            double priceAtTransaction = transaction.getPriceAtTransaction();
            System.out.println("priceAtTransaction is " + priceAtTransaction);
            double amountInvested = transaction.getAmountInvested();
            System.out.println("amountInvested is " + amountInvested);
            double quantity = amountInvested / priceAtTransaction;
            System.out.println("quantity is " + quantity);

            CryptoCurrency cryptoCurrency = transaction.getCryptoCurrency();
            System.out.println("cryptoCurrency is " + cryptoCurrency);
            if (cryptoCurrency == null) {
                throw new IllegalArgumentException("La crypto-monnaie n'existe pas.");
            }

            double currentPrice =cryptoCurrency.getPrice();
            double currentValue = quantity * currentPrice;
            double gainOrLoss = currentValue - amountInvested;

            totalInvested += amountInvested;
            System.out.println("totalInvested is " + totalInvested);
            totalCurrentValue += currentValue;
            System.out.println("totalCurrentValue is " + totalCurrentValue);

            cryptoPerformances.add(new CryptoPerformance(
                    transaction.getCryptoCurrency().getId(),
                    amountInvested,
                    currentValue,
                    gainOrLoss
            ));
        }

        double totalGainOrLoss = totalCurrentValue - totalInvested;

        if (Math.abs(totalGainOrLoss) >= totalInvested * 0.1) {
            checkPerformance(userId, totalGainOrLoss);
        }

        return new PorfolioPerformance(totalInvested, totalCurrentValue, totalGainOrLoss, cryptoPerformances);
    }

    private void checkPerformance(Long userId, double totalGainOrLoss) {
        User user = userRepository.findById(userId).orElse(null);
        assert user != null;
        String subject = "Alerte : Performance de votre portefeuille";
        String message = totalGainOrLoss > 0 ?
                String.format("Bonne nouvelle ! Vous avez réalisé un gain de %.2f € sur votre portefeuille.", totalGainOrLoss) :
                String.format("Attention ! Vous avez une perte de %.2f € sur votre portefeuille.", totalGainOrLoss);

        String email = user.getEmail();
        emailService.sendEmail(email, subject, message);
    }
}
