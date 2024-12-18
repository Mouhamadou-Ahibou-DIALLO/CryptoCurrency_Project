package com.cryptocurrency.data.repository;

import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.model.MarketData;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * The MarketDataRepository interface is a Spring Data JPA repository for managing market data.
 * MarketDataRepository interface.
 * Author: Mouhamadou Ahibou DIALLO
 */
@Repository
public interface MarketDataRepository extends JpaRepository<MarketData, Long> {


    /**
     * Find a market data entry for a given cryptocurrency.
     *
     * @param cryptoCurrency the cryptocurrency to find the market data for
     * @return an Optional containing the market data entry if found, or an empty Optional if not
     */
    Optional<MarketData> findByCryptoCurrency(CryptoCurrency cryptoCurrency);

    /**
     * Find all market data entries for a given timestamp.
     *
     * @param timeStamp the timestamp to find market data for
     * @return a list of market data entries for the given timestamp
     */
    List<MarketData> findByTimeStamp(LocalDateTime timeStamp);

    /**
     * Find all market data entries for a given price in USD.
     *
     * @param priceUsd the price in USD to find market data for
     * @return a list of market data entries for the given price
     */
    List<MarketData> findByPriceUsd(Double priceUsd);

    /**
     * Find all market data entries for a given volume in USD.
     *
     * @param volumeUsd the volume in USD to find market data for
     * @return a list of market data entries for the given volume
     */
    List<MarketData> findByVolumeUsd(Double volumeUsd);

    /**
     * Find all market data entries for a given market capitalization in USD.
     *
     * @param marketCapUsd the market capitalization in USD to find market data for
     * @return a list of market data entries for the given market capitalization
     */
    List<MarketData> findByMarketCapUsd(Double marketCapUsd);
}
