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
    @JoinColumn(name = "crypto_id", nullable = false)
    private CryptoCurrency cryptoCurrency;

    private Double priceThreshold;
    private Double variationThreshold;

    public Alerts() {}

    public Alerts(Long id, User user, CryptoCurrency cryptoCurrency, Double priceThreshold, Double variationThreshold) {
        this.id = id;
        this.user = user;
        this.cryptoCurrency = cryptoCurrency;
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
