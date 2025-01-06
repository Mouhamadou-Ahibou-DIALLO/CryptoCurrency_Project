package com.cryptocurrency.data.model;

import java.util.List;

/**
 * Class PorfolioPerformance to store the performance of a user's portfolio.
 * @author Mouhamadou Ahibou DIALLO
 */
public class PorfolioPerformance {

    /**
     * The total amount invested in the portfolio.
     */
    private double totalInvested;

    /**
     * The total current value of the portfolio.
     */
    private double totalCurrentValue;

    /**
     * The total gain or loss of the portfolio.
     */
    private double totalGainOrLoss;

    /**
     * A list of CryptoPerformance objects representing the performance of each cryptocurrency in the portfolio.
     */
    private List<CryptoPerformance> cryptoPerformances;

    /**
     * Returns a list of CryptoPerformance objects representing the performance of each cryptocurrency in the portfolio.
     * @return A list of CryptoPerformance objects.
     */
    public List<CryptoPerformance> getCryptoPerformances() {
        return cryptoPerformances;
    }

    /**
     * Constructor for the PorfolioPerformance class.
     * @param totalInvested The total amount invested in the portfolio.
     * @param totalCurrentValue The total current value of the portfolio.
     * @param totalGainOrLoss The total gain or loss of the portfolio.
     * @param cryptoPerformances A list of CryptoPerformance objects representing the performance of each cryptocurrency in the portfolio.
     */
    public PorfolioPerformance(double totalInvested, double totalCurrentValue, double totalGainOrLoss, List<CryptoPerformance> cryptoPerformances) {
        this.totalInvested = totalInvested;
        this.totalCurrentValue = totalCurrentValue;
        this.totalGainOrLoss = totalGainOrLoss;
        this.cryptoPerformances = cryptoPerformances;
    }

    /**
     * Default constructor for the PorfolioPerformance class.
     */
    public PorfolioPerformance() {}

    /**
     * Sets the list of CryptoPerformance objects representing the performance of each cryptocurrency in the portfolio.
     *
     * @param cryptoPerformances A list of CryptoPerformance objects to set for the portfolio.
     */
    public void setCryptoPerformances(List<CryptoPerformance> cryptoPerformances) {
        this.cryptoPerformances = cryptoPerformances;
    }

    /**
     * Returns the total amount invested in the portfolio.
     *
     * @return The total invested amount.
     */
    public double getTotalInvested() {
        return totalInvested;
    }

    /**
     * Sets the total amount invested in the portfolio.
     *
     * @param totalInvested The total amount invested in the portfolio.
     */
    public void setTotalInvested(double totalInvested) {
        this.totalInvested = totalInvested;
    }

    /**
     * Returns the total current value of the portfolio.
     * @return The total current value of the portfolio.
     */
    public double getTotalCurrentValue() {
        return totalCurrentValue;
    }

    /**
     * Sets the total current value of the portfolio.
     * @param totalCurrentValue The total current value of the portfolio.
     */
    public void setTotalCurrentValue(double totalCurrentValue) {
        this.totalCurrentValue = totalCurrentValue;
    }

    /**
     * Returns the total gain or loss of the portfolio.
     * @return The total gain or loss of the portfolio.
     */
    public double getTotalGainOrLoss() {
        return totalGainOrLoss;
    }

    /**
     * Sets the total gain or loss of the portfolio.
     * @param totalGainOrLoss The total gain or loss of the portfolio.
     */
    public void setTotalGainOrLoss(double totalGainOrLoss) {
        this.totalGainOrLoss = totalGainOrLoss;
    }
}
