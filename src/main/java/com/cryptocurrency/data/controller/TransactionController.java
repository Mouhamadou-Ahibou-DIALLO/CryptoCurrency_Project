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

@RestController
@RequestMapping("/api/portfolio")
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

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

    @DeleteMapping("/delete/{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long transactionId) {
        if (transactionId == null) {
            return ResponseEntity.badRequest().body("L'id de la transaction ne peut pas être null.");
        }

        transactionService.deleteTransaction(transactionId);
        System.out.println("deleted transaction is done");
        return ResponseEntity.ok("Transaction supprimée avec succès.");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllTransactions(@PathVariable Long userId) {
        if (userId == null) {
            return ResponseEntity.badRequest().body("L'id de l'utilisateur ne peut pas être null.");
        }

        List<Transaction> transactions = transactionService.getTransactions(userId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/performance/{userId}")
    public ResponseEntity<PorfolioPerformance> getPortfolioPerformance(@PathVariable Long userId) {
        PorfolioPerformance performance = transactionService.getPorfolioPerformance(userId);
        return ResponseEntity.ok(Objects.requireNonNullElseGet(performance, PorfolioPerformance::new));
    }
}
