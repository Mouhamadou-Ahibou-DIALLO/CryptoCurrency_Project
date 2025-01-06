package com.cryptocurrency.data.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * This class represents a Transaction object.
 * Author: Mouhamadou Ahibou DIALLO
 */
@Entity
@Table(name = "transactions")
public class Transaction {

    /*
     * The id is a Long that represents the unique identifier for the Transaction object.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * The user is a User object that represents the user involved in the transaction.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /*
     * The cryptoCurrency is a CryptoCurrency object that represents the cryptocurrency
     * involved in the transaction.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "crypto_id", nullable = false)
    private CryptoCurrency cryptoCurrency;

    private double amountInvested;

    /*
     * The price at transaction is a double that represents the price of the cryptocurrency
     * at the time of the transaction.
     */
    private double priceAtTransaction;

    /*
     * The quantity is a double that represents the amount of the cryptocurrency
     * that was involved in the transaction.
     */
    private double quantity;

    /*
     * The transaction date is a LocalDateTime object that represents the date and time
     * when the Transaction object was created.
     */
    private LocalDateTime transactionDate;


    /*
     * The constructor is used to create a Transaction object.
     * @param id The ID of the Transaction object.
     * @param user The User object associated with the Transaction object.
     * @param cryptoCurrency The CryptoCurrency object associated with the Transaction object.
     * @param amountInvested The amount invested in the Transaction object.
     * @param priceAtTransaction The price of the cryptocurrency at the time of the transaction.
     * @param quantity The quantity of the cryptocurrency involved in the transaction.
     * @param transactionDate The transaction date of the Transaction object.
     */
    public Transaction(Long id, User user, CryptoCurrency cryptoCurrency, double amountInvested, double priceAtTransaction, double quantity, LocalDateTime transactionDate) {
        this.id = id;
        this.user = user;
        this.cryptoCurrency = cryptoCurrency;
        this.amountInvested = amountInvested;
        this.priceAtTransaction = priceAtTransaction;
        this.quantity = quantity;
        this.transactionDate = transactionDate;
    }

    /*
     * The default constructor is used to create an empty Transaction object.
     */
    public Transaction() {}

    /**
     * Returns the ID of the Transaction object.
     * The ID is a unique identifier for the Transaction object.
     * @return The ID of the Transaction object.
     */
    public Long getId() { return id; }

    /**
     * Sets the ID of the Transaction object.
     * The ID is a unique identifier for the Transaction object.
     * @param id The ID to set for the Transaction object.
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Returns the User object associated with the Transaction object.
     * The User object contains the information about the user who made the transaction.
     * @return The User object associated with the Transaction object.
     */
    public User getUser() { return user; }

    /**
     * Sets the User object associated with the Transaction object.
     * The User object contains the information about the user who made the transaction.
     * @param user The User object to associate with the Transaction object.
     */
    public void setUser(User user) { this.user = user; }

    /**
     * Returns the CryptoCurrency object associated with the Transaction object.
     * The CryptoCurrency object contains the information about the crypto currency
     * in which the transaction was made.
     * @return The CryptoCurrency object associated with the Transaction object.
     */
    public CryptoCurrency getCryptoCurrency() { return cryptoCurrency; }

    /**
     * Sets the CryptoCurrency object associated with the Transaction object.
     * The CryptoCurrency object contains the information about the crypto currency
     * in which the transaction was made.
     * @param cryptoCurrency The CryptoCurrency object to associate with the Transaction object.
     */
    public void setCryptoCurrency(CryptoCurrency cryptoCurrency) { this.cryptoCurrency = cryptoCurrency; }

    /**
     * Returns the amount invested in the transaction.
     * The amount invested is the total value of the transaction.
     * @return The amount invested in the transaction.
     */
    public double getAmountInvested() { return amountInvested; }

    /**
     * Sets the amount invested in the transaction.
     * The amount invested is the total value of the transaction.
     * @param amountInvested The amount invested in the transaction.
     */
    public void setAmountInvested(double amountInvested) { this.amountInvested = amountInvested; }

    /**
     * Returns the price of the cryptocurrency at the time of the transaction.
     * The price at transaction is the cost per unit of the cryptocurrency
     * when the transaction was executed.
     *
     * @return The price of the cryptocurrency at the time of the transaction.
     */
    public double getPriceAtTransaction() { return priceAtTransaction; }

    /**
     * Sets the price of the cryptocurrency at the time of the transaction.
     * The price at transaction is the cost per unit of the cryptocurrency
     * when the transaction was executed.
     *
     * @param priceAtTransaction The price of the cryptocurrency to set at
     *                           the time of the transaction.
     */
    public void setPriceAtTransaction(double priceAtTransaction) { this.priceAtTransaction = priceAtTransaction; }

    /**
     * Returns the quantity of the cryptocurrency involved in the transaction.
     * The quantity is the amount of the cryptocurrency that was involved in the transaction,
     * and is calculated by dividing the amount invested by the price of the cryptocurrency
     * at the time of the transaction.
     *
     * @return The quantity of the cryptocurrency involved in the transaction.
     */
    public double getQuantity() { return quantity; }

    /**
     * Sets the quantity of the cryptocurrency involved in the transaction.
     *
     * @param quantity The quantity of the cryptocurrency to set.
     */
    public void setQuantity(double quantity) { this.quantity = quantity; }

    /**
     * Returns the transaction date of the Transaction object.
     * The transaction date is a LocalDateTime object that represents the date and time
     * when the Transaction object was created.
     *
     * @return The transaction date of the Transaction object.
     */
    public LocalDateTime getTransactionDate() { return transactionDate; }

    /**
     * Sets the transaction date of the Transaction object.
     * The transaction date is a LocalDateTime object that represents the date and time
     * when the Transaction object was created.
     *
     * @param transactionDate The transaction date to set for the Transaction object.
     */
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }
}
