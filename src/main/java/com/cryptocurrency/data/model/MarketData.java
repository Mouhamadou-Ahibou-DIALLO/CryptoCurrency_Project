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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
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

    public Double getPrice_usd() {
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
