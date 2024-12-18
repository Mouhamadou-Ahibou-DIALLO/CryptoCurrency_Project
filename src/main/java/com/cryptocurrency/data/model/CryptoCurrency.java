package com.cryptocurrency.data.model;

import jakarta.persistence.*;

/**
 * This class represents a CryptoCurrency object.
 * Author: Mouhamadou Ahibou DIALLO
 */
@Entity
@Table(name = "crypto_currency")
public class CryptoCurrency {

    /**
     * The ID of the CryptoCurrency.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the CryptoCurrency.
     */
    private String name;

    /**
     * The symbol of the CryptoCurrency.
     */
    private String symbol;

    /**
     * The market cap rank of the CryptoCurrency.
     */
    private int marketCapRank;

    /**
     * Constructor for the CryptoCurrency class.
     *
     * @param id The ID of the CryptoCurrency.
     * @param name The name of the CryptoCurrency.
     * @param symbol The symbol of the CryptoCurrency.
     * @param marketCapRank The market cap rank of the CryptoCurrency.
     */
    public CryptoCurrency(Long id, String name, String symbol, int marketCapRank) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.marketCapRank = marketCapRank;
    }

    /**
     * Default constructor for the CryptoCurrency class.
     */
    public CryptoCurrency() {}

    /**
     * Sets the ID of the CryptoCurrency.
     *
     * @param id The ID of the CryptoCurrency.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the ID of the CryptoCurrency.
     *
     * @return The ID of the CryptoCurrency.
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the name of the CryptoCurrency.
     *
     * @return The name of the CryptoCurrency.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the CryptoCurrency.
     *
     * @param name The name of the CryptoCurrency.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the symbol of the CryptoCurrency.
     *
     * @return The symbol of the CryptoCurrency.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Sets the symbol of the CryptoCurrency.
     *
     * @param symbol The symbol of the CryptoCurrency.
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the market capitalization rank of the CryptoCurrency.
     *
     * @return The market capitalization rank of the CryptoCurrency.
     */
    public int getMarketCapRank() {
        return marketCapRank;
    }

    /**
     * Sets the market capitalization rank of the CryptoCurrency.
     *
     * @param marketCapRank The market capitalization rank to set.
     */
    public void setMarketCapRank(int marketCapRank) {
        this.marketCapRank = marketCapRank;
    }
}
