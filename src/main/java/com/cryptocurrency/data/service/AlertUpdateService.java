package com.cryptocurrency.data.service;

public class AlertUpdateService {

    private Double priceThreshold;
    private Double variationThreshold;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
