package com.cryptocurrency.data.service;

/**
 * This class represents a service for handling transaction requests.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class TransactionRequestService {

    /**
     * The ID of the user associated with this transaction request.
     */
    private Long userId;

    /**
     * The ID of the crypto currency associated with this transaction request.
     */
    private Long cryptoId;

    /**
     * The amount invested associated with this transaction request.
     */
    private double amountInvested;

    /**
     * Default constructor for the TransactionRequestService class.
     */
    public TransactionRequestService() {}

    /**
     * Returns the ID of the user associated with this transaction request.
     *
     * @return The user ID as a Long.
     */
    public Long getUserId() { return userId; }

    /**
     * Sets the ID of the user associated with this transaction request.
     *
     * @param userId The user ID to set as a Long.
     */
    public void setUserId(Long userId) { this.userId = userId; }

    /**
     * Returns the ID of the crypto currency associated with this transaction request.
     *
     * @return The crypto currency ID as a Long.
     */
    public Long getCryptoId() { return cryptoId; }

    /**
     * Sets the ID of the crypto currency associated with this transaction request.
     *
     * @param cryptoId The crypto currency ID to set as a Long.
     */
    public void setCryptoId(Long cryptoId) { this.cryptoId = cryptoId; }

    /**
     * Returns the amount invested associated with this transaction request.
     * The amount invested is the value of the transaction that was requested.
     *
     * @return The amount invested as a double.
     */
    public double getAmountInvested() { return amountInvested; }

    /**
     * Sets the amount invested associated with this transaction request.
     * The amount invested is the value of the transaction that was requested.
     * @param amountInvested The amount invested as a double.
     */
    public void setAmountInvested(double amountInvested) { this.amountInvested = amountInvested; }
}
