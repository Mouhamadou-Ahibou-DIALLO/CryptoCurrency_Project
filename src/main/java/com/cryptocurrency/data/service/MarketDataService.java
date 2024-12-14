package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.model.MarketData;
import com.cryptocurrency.data.repository.MarketDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MarketDataService {

    @Autowired
    private MarketDataRepository marketDataRepository;

    public List<MarketData> findByCryptoCurrency(CryptoCurrency cryptoCurrency) {
        return marketDataRepository.findByCryptoCurrency(cryptoCurrency);
    }

    public List<MarketData> findByTimeStamp(LocalDateTime timeStamp) {
        return marketDataRepository.findByTimeStamp(timeStamp);
    }

    public List<MarketData> findByPriceUsd(Double priceUsd) {
        return marketDataRepository.findByPriceUsd(priceUsd);
    }

    public List<MarketData> findByVolumeUsd(Double volumeUsd) {
        return marketDataRepository.findByVolumeUsd(volumeUsd);
    }

    public List<MarketData> findByMarketCapUsd(Double marketCapUsd) {
        return marketDataRepository.findByMarketCapUsd(marketCapUsd);
    }

    public List<MarketData> findAll() {
        return marketDataRepository.findAll();
    }

    public MarketData findById(Long id) {
        Optional<MarketData> result = marketDataRepository.findById(id);
        return result.orElse(null);
    }
}
