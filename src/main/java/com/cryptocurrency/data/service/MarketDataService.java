package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.model.MarketData;
import com.cryptocurrency.data.repository.MarketDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * The service for MarketData objects.
 * Author: Mouhamadou Ahibou DIALLO
 */
@Service
public class MarketDataService {

    /**
     * The repository for MarketData objects.
     */
    @Autowired
    private MarketDataRepository marketDataRepository;

    /**
     * Finds a market data entry for a given cryptocurrency.
     *
     * @param cryptoCurrency the cryptocurrency to find the market data for
     * @return the MarketData object if found
     * @throws RuntimeException if the market data for the cryptocurrency is not found
     */
    public MarketData findByCryptoCurrency(CryptoCurrency cryptoCurrency) {
        return marketDataRepository.findByCryptoCurrency(cryptoCurrency)
                .orElseThrow(() -> new RuntimeException("Market data not found for the given cryptocurrency"));
    }

    /**
     * Finds all market data entries for a given timestamp.
     *
     * @param timeStamp the timestamp to find market data for
     * @return a list of market data entries for the given timestamp
     */
    public List<MarketData> findByTimeStamp(LocalDateTime timeStamp) {
        return marketDataRepository.findByTimeStamp(timeStamp);
    }

    /**
     * Finds all market data entries for a given price in USD.
     *
     * @param priceUsd the price in USD to find market data for
     * @return a list of market data entries for the given price
     */
    public List<MarketData> findByPriceUsd(Double priceUsd) {
        return marketDataRepository.findByPriceUsd(priceUsd);
    }

    /**
     * Finds all market data entries for a given volume in USD.
     *
     * @param volumeUsd the volume in USD to find market data for
     * @return a list of market data entries for the given volume
     */
    public List<MarketData> findByVolumeUsd(Double volumeUsd) {
        return marketDataRepository.findByVolumeUsd(volumeUsd);
    }

    /**
     * Finds all market data entries for a given market capitalization in USD.
     *
     * @param marketCapUsd the market capitalization in USD to find market data for
     * @return a list of market data entries for the given market capitalization
     */
    public List<MarketData> findByMarketCapUsd(Double marketCapUsd) {
        return marketDataRepository.findByMarketCapUsd(marketCapUsd);
    }

    /**
     * Returns a list of all the MarketData objects in the database.
     *
     * @return a list of MarketData objects
     */
    public List<MarketData> findAll() {
        return marketDataRepository.findAll();
    }

    /**
     * Finds a market data entry by its ID.
     *
     * @param id the ID of the market data entry to find
     * @return the MarketData object if found, or null if not
     */
    public MarketData findById(Long id) {
        Optional<MarketData> result = marketDataRepository.findById(id);
        return result.orElse(null);
    }
}
