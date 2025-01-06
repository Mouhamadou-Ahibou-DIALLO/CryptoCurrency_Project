package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.model.User;

public class AlertCreatedService {

    private User user;
    private CryptoCurrency cryptoCurrency;
    private Double priceThreshold;
    private Double variationThreshold;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CryptoCurrency getCryptoCurrency() {
        return cryptoCurrency;
    }

    public void setCryptoCurrency(CryptoCurrency cryptoCurrency) {
        this.cryptoCurrency = cryptoCurrency;
    }

    public Double getPriceThreshold() {
        return priceThreshold;
    }

    public void setPriceThreshold(Double priceThreshold) {
        this.priceThreshold = priceThreshold;
    }

    public Double getVariationThreshold() {
        return variationThreshold;
    }

    public void setVariationThreshold(Double variationThreshold) {
        this.variationThreshold = variationThreshold;
    }
}
