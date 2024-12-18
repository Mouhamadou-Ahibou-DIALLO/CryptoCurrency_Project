package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.Alerts;
import com.cryptocurrency.data.model.MarketData;
import com.cryptocurrency.data.model.User;
import com.cryptocurrency.data.repository.AlertsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The service for Alerts objects.
 * Author: Mouhamadou Ahibou DIALLO
 */
@Service
public class AlertsService {

    /**
     * The repository for Alerts objects.
     */
    @Autowired
    private AlertsRepository alertsRepository;

    /**
     * The service for Email objects.
     */
    @Autowired
    private EmailService emailService;

    /**
     * Find all alerts for a given user.
     *
     * @param user the User object to find alerts for
     * @return a list of alerts for the given user
     */
    public List<Alerts> findByUser(User user) {
        return alertsRepository.findByUser(user);
    }

    /**
     * Find all alerts for a given market data entry.
     *
     * @param marketData the market data entry to find alerts for
     * @return a list of alerts for the given market data entry
     */
    public List<Alerts> findByMarketData(MarketData marketData) {
        // Find all alerts for a given market data entry.
        return alertsRepository.findByMarketData(marketData);
    }

    /**
     * Find all alerts for a given price threshold.
     *
     * @param priceThreshold the price threshold to find alerts for
     * @return a list of alerts for the given price threshold
     */
    public List<Alerts> findByPriceThreshold(Double priceThreshold) {
        return alertsRepository.findByPriceThreshold(priceThreshold);
    }

    /**
     * Find all alerts for a given variation threshold.
     *
     * @param variationThreshold the variation threshold to find alerts for
     * @return a list of alerts for the given variation threshold
     */
    public List<Alerts> findByVariationThreshold(Double variationThreshold) {
        return alertsRepository.findByVariationThreshold(variationThreshold);
    }

    /**
     * Returns a list of all alerts in the database.
     *
     * @return a list of all alerts in the database
     */
    public List<Alerts> findAll() {
        return alertsRepository.findAll();
    }

    /**
     * Find an alert by its ID.
     *
     * @param id the ID of the alert to find
     * @return the alert with the given ID, or null if none is found
     */
    public Alerts findById(Long id) {
        Optional<Alerts> result = alertsRepository.findById(id);
        return result.orElse(null);
    }

    /**
     * Saves an alert to the database.
     *
     * @param alerts the alert to save
     * @return the saved alert
     */
    public Alerts save(Alerts alerts) {
        return alertsRepository.save(alerts);
    }

    /**
     * Deletes an alert by its ID.
     *
     * @param id the ID of the alert to be deleted
     */
    public void deleteById(Long id) {
        alertsRepository.deleteById(id);
    }

    /**
     * Find all alerts for a given market data entry and user.
     *
     * @param marketData the market data entry to find alerts for
     * @param user the user to find alerts for
     * @return a list of alerts for the given market data and user
     */
    public List<Alerts> findByMarketDataAndUser(MarketData marketData, User user) {
        return alertsRepository.findByMarketDataAndUser(marketData, user);
    }

    /**
     * Deletes all alerts associated with the specified user.
     *
     * @param user the user whose alerts are to be deleted
     */
    public void deleteByUser(User user) {
        alertsRepository.deleteByUser(user);
    }

    /**
     * Check all alerts to see if any have been triggered.
     */
    public void checkAlerts() {
        List<Alerts> alerts = alertsRepository.findAll();

        for (Alerts alert : alerts) {
            double currentPrice = getCurrentPrice(alert.getMarketData());

            if (currentPrice >= alert.getPriceThreshold()) {
                emailService.sendNotification(alert, currentPrice);
            }
        }
    }

    /**
     * Gets the current price for a given market data entry.
     *
     * @param marketData the market data entry to get the current price for
     * @return the current price for the given market data entry
     */
    private double getCurrentPrice(MarketData marketData) {
        return marketData.getPriceUsd();
    }

    /**
     * Creates a new alert for the given user.
     *
     * @param user the user to create the alert for
     * @param alert the alert to create
     * @return the created alert
     */
    public Alerts createAlert(User user, Alerts alert) {
        alert.setUser(user);
        return alertsRepository.save(alert);
    }
}

/*
if (alert.getVariationThreshold() != null) {
            Double previousPrice = marketDataService.getPreviousPrice(alert.getMarketData());
            Double priceChange = ((currentPrice - previousPrice) / previousPrice) * 100;

            if (Math.abs(priceChange) >= alert.getVariationThreshold()) {
                sendEmailNotification(alert.getUser(), alert);
            }

    public void checkTechnicalIndicators() {
    List<Alert> alerts = alertRepository.findAll();

    for (Alert alert : alerts) {
        // Vérifier l'indicateur technique
        Double rsi = marketDataService.getRSI(alert.getMarketData());

        if (rsi > alert.getTechnicalThreshold()) {
            sendEmailNotification(alert.getUser(), alert);
        }
    }
}
 */
