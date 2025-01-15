package com.cryptocurrency.data.service;

/**
 * This class represents a service for handling transaction updates.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class TransactionUpdateService {

    /**
     * The amount invested in the transaction.
     */
    private double amountInvested;

    /**
     * Default Constructor for the TransactionUpdateService class.
     */
    public TransactionUpdateService() {}

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
}
