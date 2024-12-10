package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.Alerts;
import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.model.User;
import com.cryptocurrency.data.repository.AlertsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertsService {

    private AlertsRepository alertsRepository;

    public List<Alerts> findByUser(User user) {
        return alertsRepository.findByUser(user);
    }

    public List<Alerts> findByCryptoCurrency(CryptoCurrency cryptoCurrency) {
        return alertsRepository.findByCryptoCurrency(cryptoCurrency);
    }

    public List<Alerts> findByPriceThreshold(Double priceThreshold) {
        return alertsRepository.findByPriceThreshold(priceThreshold);
    }

    public List<Alerts> findByVariationThreshold(Double variationThreshold) {
        return alertsRepository.findByVariationThreshold(variationThreshold);
    }

    public List<Alerts> findAll() {
        return alertsRepository.findAll();
    }

    public Alerts findById(Long id) {
        Optional<Alerts> result = alertsRepository.findById(id);
        return result.orElse(null);
    }

    public Alerts save(Alerts alerts) {
        return alertsRepository.save(alerts);
    }

    public void deleteById(Long id) {
        alertsRepository.deleteById(id);
    }

    public List<Alerts> findByCryptoCurrencyAndUser(CryptoCurrency cryptoCurrency, User user) {
        return alertsRepository.findByCryptoCurrencyAndUser(cryptoCurrency, user);
    }
}
