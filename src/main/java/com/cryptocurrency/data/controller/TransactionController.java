package com.cryptocurrency.data.controller;

import com.cryptocurrency.data.model.PorfolioPerformance;
import com.cryptocurrency.data.model.Transaction;
import com.cryptocurrency.data.service.TransactionRequestService;

import com.cryptocurrency.data.service.TransactionService;
import com.cryptocurrency.data.service.TransactionUpdateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * The TransactionController class is a Spring REST controller for managing transactions.
 * Author: Mouhamadou Ahibou DIALLO
 */
@RestController
@RequestMapping("/api/portfolio")
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {

    /**
     * The transactionService field is a Spring service for managing transactions.
     */
    @Autowired
    private TransactionService transactionService;

    /**
     * Creates a new transaction for a user with a specified cryptocurrency and investment amount.
     *
     * @param transaction A TransactionRequestService object containing userId, cryptoId, and amountInvested.
     * @return A ResponseEntity containing the created Transaction object or an error message if userId or cryptoId is null.
     */
    @PostMapping("/create")
    public ResponseEntity<?> createTransaction(@RequestBody TransactionRequestService transaction) {
        Long userId = transaction.getUserId();
        if (userId == null) {
            return ResponseEntity.badRequest().body("L'utilisateur ne peut pas être null.");
        }

        Long cryptoId = transaction.getCryptoId();
        if (cryptoId == null) {
            return ResponseEntity.badRequest().body("La crypto-monnaie ne peut pas être null.");
        }
        double amountInvested = transaction.getAmountInvested();

        Transaction newTransaction = transactionService.createTransaction(userId, cryptoId, amountInvested);
        System.out.println("created transaction is done");
        return ResponseEntity.ok(newTransaction);
    }

    /**
     * Updates a transaction with a specified ID with a new investment amount.
     *
     * @param transactionId The ID of the transaction to be updated.
     * @param transaction A TransactionUpdateService object containing the new investment amount.
     * @return A ResponseEntity containing the updated Transaction object or an error message if transactionId is null.
     */
    @PutMapping("/update/{transactionId}")
    public ResponseEntity<?> updateTransaction(@PathVariable Long transactionId, @RequestBody TransactionUpdateService transaction) {
        if (transactionId == null) {
            return ResponseEntity.badRequest().body("L'id de la transaction ne peut pas être null.");
        }
        double amountInvested = transaction.getAmountInvested();

        Transaction updatedTransaction = transactionService.updateTransaction(transactionId, amountInvested);
        System.out.println("updated transaction is done");
        return ResponseEntity.ok(updatedTransaction);
    }

    /**
     * Deletes a transaction with the given ID.
     *
     * @param transactionId The ID of the transaction to be deleted.
     * @return A ResponseEntity containing an error message if transactionId is null or a success message if the transaction is deleted.
     */
    @DeleteMapping("/delete/{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long transactionId) {
        if (transactionId == null) {
            return ResponseEntity.badRequest().body("L'id de la transaction ne peut pas être null.");
        }

        transactionService.deleteTransaction(transactionId);
        System.out.println("deleted transaction is done");
        return ResponseEntity.ok("Transaction supprimée avec succès.");
    }

    /**
     * Retrieves a list of all transactions associated with a given user.
     *
     * @param userId The ID of the user whose transactions are to be retrieved.
     * @return A ResponseEntity containing a list of Transaction objects associated with the user, or an error message if userId is null.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllTransactions(@PathVariable Long userId) {
        if (userId == null) {
            return ResponseEntity.badRequest().body("L'id de l'utilisateur ne peut pas être null.");
        }

        List<Transaction> transactions = transactionService.getTransactions(userId);
        return ResponseEntity.ok(transactions);
    }

/**
 * Retrieves the performance of a user's portfolio.
 *
 * @param userId The ID of the user whose portfolio performance is to be retrieved.
 * @return A ResponseEntity containing a PorfolioPerformance object representing the user's portfolio performance.
 *         If the performance is null, an empty PorfolioPerformance object is returned.
 */
    @GetMapping("/performance/{userId}")
    public ResponseEntity<PorfolioPerformance> getPortfolioPerformance(@PathVariable Long userId) {
        PorfolioPerformance performance = transactionService.getPorfolioPerformance(userId);
        return ResponseEntity.ok(Objects.requireNonNullElseGet(performance, PorfolioPerformance::new));
    }
}
