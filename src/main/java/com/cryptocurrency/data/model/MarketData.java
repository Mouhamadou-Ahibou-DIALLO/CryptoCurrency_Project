package com.cryptocurrency.data.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * This class represents a MarketData object.
 * Author: Mouhamadou Ahibou DIALLO
 */
@Entity
@Table(name = "market_data")
public class MarketData {

    /**
     * The ID of the MarketData object.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The CryptoCurrency object associated with the MarketData object.
     */
    @ManyToOne
    @JoinColumn(name = "crypto_id", nullable = false)
    private CryptoCurrency cryptoCurrency;

    /**
     * The timestamp of the MarketData object.
     */
    private LocalDateTime timeStamp;

    /**
     * The price in USD of the MarketData object.
     */
    private Double priceUsd;

    /**
     * The volume in USD of the MarketData object.
     */
    private Double volumeUsd;

    /**
     * The market capitalization in USD of the MarketData object.
     */
    private Double marketCapUsd;

    /**
     * The constructor for the MarketData object.
     * @param id The ID of the MarketData object.
     * @param cryptoCurrency The CryptoCurrency object associated with the MarketData object.
     * @param timeStamp The timestamp of the MarketData object.
     * @param priceUsd The price in USD of the MarketData object.
     * @param volumeUsd The volume in USD of the MarketData object.
     * @param marketCapUsd The market capitalization in USD of the MarketData object.
     */
    public MarketData(Long id, CryptoCurrency cryptoCurrency, LocalDateTime timeStamp, Double priceUsd, Double volumeUsd, Double marketCapUsd) {
        this.id = id;
        this.cryptoCurrency = cryptoCurrency;
        this.timeStamp = timeStamp;
        this.priceUsd = priceUsd;
        this.volumeUsd = volumeUsd;
        this.marketCapUsd = marketCapUsd;
    }

    /**
     * The default constructor for the MarketData object.
     */
    public MarketData() {}

    /**
     * Sets the ID of the MarketData object.
     *
     * @param id The ID to set for the MarketData object.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the ID of the MarketData object.
     *
     * @return The ID of the MarketData object.
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the CryptoCurrency object associated with the MarketData object.
     *
     * @return The CryptoCurrency object associated with the MarketData object.
     */
    public CryptoCurrency getCryptoCurrency() {
        return cryptoCurrency;
    }

    /**
     * Sets the CryptoCurrency object associated with the MarketData object.
     *
     * @param cryptoCurrency The CryptoCurrency object to associate with the MarketData object.
     */
    public void setCryptoCurrency(CryptoCurrency cryptoCurrency) {
        this.cryptoCurrency = cryptoCurrency;
    }

    /**
     * Sets the timestamp of the MarketData object.
     *
     * @param timeStamp The timestamp to set for the MarketData object.
     */
    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Returns the timestamp of the MarketData object.
     *
     * @return The timestamp of the MarketData object.
     */
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    /**
     * Returns the price of the associated CryptoCurrency in USD at the given timestamp.
     *
     * @return The price of the associated CryptoCurrency in USD at the given timestamp.
     */
    public Double getPriceUsd() {
        return priceUsd;
    }

    /**
     * Sets the price of the associated CryptoCurrency in USD at the given timestamp.
     *
     * @param priceUsd The price to set for the associated CryptoCurrency in USD at the given timestamp.
     */
    public void setPriceUsd(Double priceUsd) {
        this.priceUsd = priceUsd;
    }

    /**
     * Returns the trading volume in USD of the associated CryptoCurrency at the given timestamp.
     *
     * @return The trading volume in USD.
     */
    public Double getVolumeUsd() {
        return volumeUsd;
    }

    /**
     * Sets the trading volume in USD of the associated CryptoCurrency at the given timestamp.
     *
     * @param volumeUsd The trading volume in USD to set for the associated CryptoCurrency at the given timestamp.
     */
    public void setVolumeUsd(Double volumeUsd) {
        this.volumeUsd = volumeUsd;
    }

    /**
     * Returns the market capitalization of the associated CryptoCurrency in USD at the given timestamp.
     *
     * @return The market capitalization of the associated CryptoCurrency in USD at the given timestamp.
     */
    public Double getMarketCapUsd() {
        return marketCapUsd;
    }

    /**
     * Sets the market capitalization of the associated CryptoCurrency in USD at the given timestamp.
     *
     * @param marketCapUsd The market capitalization to set for the associated CryptoCurrency in USD at the given timestamp.
     */
    public void setMarketCapUsd(Double marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }
}
