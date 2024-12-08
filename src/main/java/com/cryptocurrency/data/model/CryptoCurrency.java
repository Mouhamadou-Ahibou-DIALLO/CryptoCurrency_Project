package com.cryptocurrency.data.model;

import jakarta.persistence.*;

@Entity
@Table(name = "crypto_currency")
public class CryptoCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String symbol;
    private int marketCapRank;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getMarketCapRank() {
        return marketCapRank;
    }

    public void setMarketCapRank(int marketCapRank) {
        this.marketCapRank = marketCapRank;
    }
}
