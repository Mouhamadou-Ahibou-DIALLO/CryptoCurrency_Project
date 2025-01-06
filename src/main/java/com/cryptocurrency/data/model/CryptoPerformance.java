package com.cryptocurrency.data.model;

/**
 * This class represents a CryptoPerformance object.
 * @author Mouhamadou Ahibou DIALLO
 */
public class CryptoPerformance {

    /**
     * The id of the crypto currency.
     */
    private Long cryptoId;

    /**
     * The amount invested in the crypto currency.
     */
    private double investedAmount;

    /**
     * The current value of the crypto currency.
     */
    private double currentValue;

    /**
     * The gain or loss of the crypto currency.
     */
    private double gainOrLoss;

    /**
     * Constructor of the CryptoPerformance class.
     * @param cryptoId The id of the crypto currency.
     * @param investedAmount The amount invested in the crypto currency.
     * @param currentValue The current value of the crypto currency.
     * @param gainOrLoss The gain or loss of the crypto currency.
     */
    public CryptoPerformance(Long cryptoId, double investedAmount, double currentValue, double gainOrLoss) {
        this.cryptoId = cryptoId;
        this.investedAmount = investedAmount;
        this.currentValue = currentValue;
        this.gainOrLoss = gainOrLoss;
    }

    /**
     * Default constructor of the CryptoPerformance class.
     */
    public CryptoPerformance() {}

    /**
     * Returns the ID of the crypto currency.
     *
     * @return The crypto currency ID.
     */
    public Long getCryptoId() {
        return cryptoId;
    }

    /**
     * Sets the ID of the crypto currency.
     *
     * @param cryptoId The crypto currency ID to set.
     */
    public void setCryptoId(Long cryptoId) {
        this.cryptoId = cryptoId;
    }

    /**
     * Returns the amount invested in the crypto currency.
     *
     * @return The amount invested in the crypto currency.
     */
    public double getInvestedAmount() {
        return investedAmount;
    }

    /**
     * Sets the amount invested in the crypto currency.
     *
     * @param investedAmount The amount invested to set.
     */
    public void setInvestedAmount(double investedAmount) {
        this.investedAmount = investedAmount;
    }

    /**
     * Returns the current value of the crypto currency.
     *
     * @return The current value of the crypto currency.
     */
    public double getCurrentValue() {
        return currentValue;
    }

    /**
     * Sets the current value of the crypto currency.
     *
     * @param currentValue The current value of the crypto currency to set.
     */
    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    /**
     * Returns the gain or loss of the crypto currency.
     *
     * @return The gain or loss of the crypto currency.
     */
    public double getGainOrLoss() {
        return gainOrLoss;
    }

    /**
     * Sets the gain or loss of the crypto currency.
     *
     * @param gainOrLoss The gain or loss to set for the crypto currency.
     */
    public void setGainOrLoss(double gainOrLoss) {
        this.gainOrLoss = gainOrLoss;
    }
}
