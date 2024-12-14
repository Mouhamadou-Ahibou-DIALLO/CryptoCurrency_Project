package com.cryptocurrency.data.model;

import jakarta.persistence.*;

@Entity
@Table(name = "alerts")
public class Alerts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "market_id", nullable = false)
    private MarketData marketData;

    private Double priceThreshold;
    private Double variationThreshold;

    public Alerts() {}

    public Alerts(Long id, User user, MarketData marketData, Double priceThreshold, Double variationThreshold) {
        this.id = id;
        this.user = user;
        this.marketData = marketData;
        this.priceThreshold = priceThreshold;
        this.variationThreshold = variationThreshold;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public MarketData getMarketData() {
        return marketData;
    }

    public void setMarketData(MarketData marketData) {
        this.marketData = marketData;
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
