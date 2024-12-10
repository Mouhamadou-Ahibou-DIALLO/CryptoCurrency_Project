package com.cryptocurrency.data.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "market_data")
public class MarketData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crypto_id", nullable = false)
    private CryptoCurrency cryptoCurrency;

    private LocalDateTime timeStamp;
    private Double priceUsd;
    private Double volumeUsd;
    private Double marketCapUsd;

    public MarketData(Long id, CryptoCurrency cryptoCurrency, LocalDateTime timeStamp, Double priceUsd, Double volumeUsd, Double marketCapUsd) {
        this.id = id;
        this.cryptoCurrency = cryptoCurrency;
        this.timeStamp = timeStamp;
        this.priceUsd = priceUsd;
        this.volumeUsd = volumeUsd;
        this.marketCapUsd = marketCapUsd;
    }

    public MarketData() {}

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

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(Double priceUsd) {
        this.priceUsd = priceUsd;
    }

    public Double getVolumeUsd() {
        return volumeUsd;
    }

    public void setVolumeUsd(Double volumeUsd) {
        this.volumeUsd = volumeUsd;
    }

    public Double getMarketCapUsd() {
        return marketCapUsd;
    }

    public void setMarketCapUsd(Double marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }
}
