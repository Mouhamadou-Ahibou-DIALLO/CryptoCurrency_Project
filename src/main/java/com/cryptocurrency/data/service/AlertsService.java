package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.Alerts;
import com.cryptocurrency.data.model.MarketData;
import com.cryptocurrency.data.model.User;
import com.cryptocurrency.data.repository.AlertsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertsService {

    @Autowired
    private AlertsRepository alertsRepository;

    @Autowired
    private EmailService emailService;

    public List<Alerts> findByUser(User user) {
        return alertsRepository.findByUser(user);
    }

    public List<Alerts> findByMarketData(MarketData marketData) {
        return alertsRepository.findByMarketData(marketData);
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

    public List<Alerts> findByMarketDataAndUser(MarketData marketData, User user) {
        return alertsRepository.findByMarketDataAndUser(marketData, user);
    }

    public void deleteByUser(User user) {
        alertsRepository.deleteByUser(user);
    }

    public void checkAlerts() {
        List<Alerts> alerts = alertsRepository.findAll();

        for (Alerts alert : alerts) {
            double currentPrice = getCurrentPrice(alert.getMarketData());
            if (currentPrice >= alert.getPriceThreshold()) {
                emailService.sendNotification(alert, currentPrice);
            }
        }
    }

    private double getCurrentPrice(MarketData marketData) {
        return marketData.getPriceUsd();
    }
}
