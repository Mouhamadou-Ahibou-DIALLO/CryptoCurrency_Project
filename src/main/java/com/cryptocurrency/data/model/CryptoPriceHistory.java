package com.cryptocurrency.data.model;

import java.time.LocalDateTime;

/**
 * This class represents a CryptoPriceHistory object.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class CryptoPriceHistory {

    /**
     * The timestamp of the CryptoPriceHistory object.
     */
    private LocalDateTime timestamp;

    /**
     * The price of the CryptoCurrency at the given timestamp.
     */
    private Double price;

    /**
     * The volume of the CryptoCurrency at the given timestamp.
     */
    private Double volume;

    /**
     * The market cap of the CryptoCurrency at the given timestamp.
     */
    private Double market;

    /**
     * Default constructor for the CryptoPriceHistory object.
     */
    public CryptoPriceHistory() {}

    /**
     * Constructor for the CryptoPriceHistory object.
     *
     * @param timestamp The timestamp of the CryptoPriceHistory object.
     * @param price The price of the CryptoCurrency at the given timestamp.
     * @param volume The volume of the CryptoCurrency at the given timestamp.
     * @param market The market cap of the CryptoCurrency at the given timestamp.
     */
    public CryptoPriceHistory(LocalDateTime timestamp, Double price, Double volume, Double market) {
        this.timestamp = timestamp;
        this.price = price;
        this.volume = volume;
        this.market = market;
    }

    /**
     * Returns the timestamp of the CryptoPriceHistory object.
     * The timestamp is a LocalDateTime object that represents the date and time
     * when the CryptoPriceHistory object was created.
     *
     * @return The timestamp of the CryptoPriceHistory object.
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Returns the price of the CryptoCurrency at the given timestamp.
     * The price is a Double object that represents the price of the CryptoCurrency
     * at the given timestamp.
     *
     * @return The price of the CryptoCurrency at the given timestamp.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Returns the trading volume of the CryptoCurrency at the given timestamp.
     * The volume is a Double object that represents the amount of the CryptoCurrency
     * traded during the given time period.
     *
     * @return The trading volume of the CryptoCurrency at the given timestamp.
     */
    public Double getVolume() {
        return volume;
    }

    /**
     * Returns the market capitalization of the CryptoCurrency at the given timestamp.
     * The market capitalization is a Double object that represents the total value
     * of all the CryptoCurrency in circulation at the given timestamp.
     *
     * @return The market capitalization of the CryptoCurrency at the given timestamp.
     */
    public Double getMarket() {
        return market;
    }

    /**
     * Sets the timestamp of the CryptoPriceHistory object.
     * The timestamp is a LocalDateTime object that represents the date and time
     * when the CryptoPriceHistory object was created.
     *
     * @param timestamp The timestamp of the CryptoPriceHistory object.
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Sets the price of the CryptoCurrency at the given timestamp.
     *
     * @param price The price of the CryptoCurrency to set.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Sets the 24-hour trading volume of the CryptoCurrency at the given timestamp.
     * The volume is a Double object that represents the amount of the CryptoCurrency
     * traded during the given time period.
     *
     * @param volume The 24-hour trading volume to set.
     */
    public void setVolume(Double volume) {
        this.volume = volume;
    }

    /**
     * Sets the market capitalization of the CryptoCurrency at the given timestamp.
     * The market capitalization is a Double object that represents the total value
     * of all the CryptoCurrency in circulation at the given timestamp.
     *
     * @param market The market capitalization to set.
     */
    public void setMarket(Double market) {
        this.market = market;
    }

    public String toString() {
        return "CryptoPriceHistory{" +
                "timestamp=" + timestamp +
                ", price=" + price +
                ", volume=" + volume +
                ", market=" + market +
                '}';
    }
}
