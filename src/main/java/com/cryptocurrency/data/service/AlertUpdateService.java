package com.cryptocurrency.data.service;

/**
 * This class represents an AlertUpdateService object.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class AlertUpdateService {

    /**
     * The name of the alert.
     */
    private Double priceThreshold;

    /**
     * The price threshold for the alert.
     */
    private Double variationThreshold;

    /**
     * The name of the alert.
     */
    private String name;

    /**
     * Default constructor for the AlertUpdateService class.
     */
    public AlertUpdateService() {}

    /**
     * Constructor for the AlertUpdateService class.
     * @param priceThreshold The price threshold for the alert.
     * @param variationThreshold The variation threshold for the alert.
     * @param name The name of the alert.
     */
    public AlertUpdateService(Double priceThreshold, Double variationThreshold, String name) {
        this.priceThreshold = priceThreshold;
        this.variationThreshold = variationThreshold;
        this.name = name;
    }

    /**
     * Returns the name of the alert.
     *
     * @return The name of the alert.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the alert.
     *
     * @param name The name to set for the alert.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the price threshold associated with the AlertUpdateService.
     * The price threshold is the price above or below which the alert will be triggered.
     * @return The price threshold associated with the AlertUpdateService.
     */
    public Double getPriceThreshold() {
        return priceThreshold;
    }

    /**
     * Sets the price threshold for the alert.
     * The price threshold is the value above or below which the alert will be triggered.
     *
     * @param priceThreshold The price threshold to set for the alert.
     */
    public void setPriceThreshold(Double priceThreshold) {
        this.priceThreshold = priceThreshold;
    }

    /**
     * Returns the variation threshold associated with the AlertUpdateService.
     * The variation threshold is the amount of variation of the price above
     * or below which the alert will be triggered.
     * @return The variation threshold associated with the AlertUpdateService.
     */
    public Double getVariationThreshold() {
        return variationThreshold;
    }

    /**
     * Sets the variation threshold for the alert.
     * The variation threshold is the amount of variation of the price above
     * or below which the alert will be triggered.
     *
     * @param variationThreshold The variation threshold to set for the alert.
     */
    public void setVariationThreshold(Double variationThreshold) {
        this.variationThreshold = variationThreshold;
    }
}
