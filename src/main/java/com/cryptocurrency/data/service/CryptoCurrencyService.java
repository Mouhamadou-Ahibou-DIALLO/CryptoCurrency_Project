package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.repository.CryptoCurrencyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoCurrencyService {

    @Autowired
    private CryptoCurrencyRepository cryptoCurrencyRepository;

    public List<CryptoCurrency> getCryptoCurrencyByMarketCapRank(int marketCapRank) {
        return cryptoCurrencyRepository.findByMarketCapRank(marketCapRank);
    }

    public List<CryptoCurrency> getCryptoCurrencyBySymbol(String symbol) {
        return cryptoCurrencyRepository.findBySymbol(symbol);
    }

    public List<CryptoCurrency> getCryptoCurrencyByName(String name) {
        return cryptoCurrencyRepository.findByName(name);
    }

    public List<CryptoCurrency> getAllCryptoCurrency() {
        return cryptoCurrencyRepository.findAll();
    }

    public CryptoCurrency getCryptoCurrencyById(Long id) {
        return cryptoCurrencyRepository.findById(id).orElse(null);
    }
}
