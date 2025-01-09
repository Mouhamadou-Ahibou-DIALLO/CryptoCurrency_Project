package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.model.User;

/**
 * This class represents an AlertCreatedService object.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class AlertCreatedService {

    /**
     * The user is a User object that represents the user associated with the alert.
     */
    private User user;

    /**
     * The cryptoCurrency is a CryptoCurrency object that represents the cryptocurrency associated with the alert.
     */
    private CryptoCurrency cryptoCurrency;

    /**
     * The priceThreshold is a double that represents the price threshold for the alert.
     */
    private Double priceThreshold;

    /**
     * The variationThreshold is a double that represents the variation threshold for the alert.
     */
    private Double variationThreshold;

    /**
     * The name is a String that represents the name of the alert.
     */
    private String name;

    /**
     * Default constructor for the AlertCreatedService class.
     */
    public AlertCreatedService() {}

    /**
     * Constructor for the AlertCreatedService class.
     *
     * @param user The User object associated with the alert.
     * @param cryptoCurrency The CryptoCurrency object associated with the alert.
     * @param priceThreshold The price threshold for the alert.
     * @param variationThreshold The variation threshold for the alert.
     * @param name The name of the alert.
     */
    public AlertCreatedService(User user, CryptoCurrency cryptoCurrency, Double priceThreshold, Double variationThreshold, String name) {
        this.user = user;
        this.cryptoCurrency = cryptoCurrency;
        this.priceThreshold = priceThreshold;
        this.variationThreshold = variationThreshold;
        this.name = name;
    }

    /**
     * Returns the name of the AlertCreatedService.
     *
     * @return The name of the AlertCreatedService.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the AlertCreatedService.
     *
     * @param name The name of the AlertCreatedService.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the User object associated with the AlertCreatedService.
     * The User object contains the information about the user who created the alert.
     * @return The User object associated with the AlertCreatedService.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the User object associated with the AlertCreatedService.
     * The User object contains information about the user who created the alert.
     *
     * @param user The User object to associate with the AlertCreatedService.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns the CryptoCurrency object associated with the AlertCreatedService.
     * The CryptoCurrency object contains the information about the crypto currency
     * in which the alert was created.
     * @return The CryptoCurrency object associated with the AlertCreatedService.
     */
    public CryptoCurrency getCryptoCurrency() {
        return cryptoCurrency;
    }

    /**
     * Sets the CryptoCurrency object associated with the AlertCreatedService.
     * The CryptoCurrency object contains the information about the crypto currency
     * for which the alert was created.
     *
     * @param cryptoCurrency The CryptoCurrency object to set in the AlertCreatedService.
     */
    public void setCryptoCurrency(CryptoCurrency cryptoCurrency) {
        this.cryptoCurrency = cryptoCurrency;
    }

    /**
     * Returns the price threshold associated with the AlertCreatedService.
     * The price threshold is the price above or below which the alert will be triggered.
     * @return The price threshold associated with the AlertCreatedService.
     */
    public Double getPriceThreshold() {
        return priceThreshold;
    }

    /**
     * Sets the price threshold associated with the AlertCreatedService.
     * The price threshold is the price above or below which the alert will be triggered.
     *
     * @param priceThreshold The price threshold associated with the AlertCreatedService.
     */
    public void setPriceThreshold(Double priceThreshold) {
        this.priceThreshold = priceThreshold;
    }

    /**
     * Returns the variation threshold associated with the AlertCreatedService.
     * The variation threshold is the amount of variation of the price above
     * or below which the alert will be triggered.
     * @return The variation threshold associated with the AlertCreatedService.
     */
    public Double getVariationThreshold() {
        return variationThreshold;
    }

    /**
     * Sets the variation threshold associated with the AlertCreatedService.
     * The variation threshold is the amount of variation of the price above
     * or below which the alert will be triggered.
     *
     * @param variationThreshold The variation threshold associated with the AlertCreatedService.
     */
    public void setVariationThreshold(Double variationThreshold) {
        this.variationThreshold = variationThreshold;
    }
}
