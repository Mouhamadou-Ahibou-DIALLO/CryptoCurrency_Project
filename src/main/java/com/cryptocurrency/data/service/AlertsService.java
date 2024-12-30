package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.Alerts;
import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.model.User;
import com.cryptocurrency.data.repository.AlertsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
     * Deletes all alerts associated with the specified user.
     *
     * @param user the user whose alerts are to be deleted
     */
    public void deleteByUser(User user) {
        alertsRepository.deleteByUser(user);
    }

    /**
     * Checks all alerts in the database and sends notifications to users if the current
     * price of the cryptocurrency exceeds their specified price threshold or if the price
     * change percentage exceeds their specified variation threshold.
     * This method retrieves all alerts, iterates through each alert, and checks the
     * current price and change percentage of the associated cryptocurrency. If the
     * conditions are met, it sends an email notification to the user.
     */
    public void checkAlerts() {
        List<Alerts> alerts = alertsRepository.findAll();

        for (Alerts alert : alerts) {
            List<Alerts> alertsUser = findByUser(alert.getUser());
            CryptoCurrency cryptoCurrency = alert.getCryptoCurrency();

            for (Alerts alertUser : alertsUser) {
                Double currentPrice = cryptoCurrency.getPrice();
                Double changePercentage = cryptoCurrency.getChange();

                if (currentPrice > alertUser.getPriceThreshold()) {
                    emailService.sendNotification(alertUser, currentPrice);
                }

                if (Math.abs(changePercentage) > alertUser.getVariationThreshold()) {
                    emailService.sendNotification(alertUser, changePercentage);
                }
            }
        }
    }

    /**
     * Creates a new alert for the given user.
     *
     * @param user the user to create the alert for
     * @param alert the alert to create
     * @return the created alert
     */
    public Alerts createAlert(User user, Alerts alert) {
        if (user == null) {
            throw new IllegalArgumentException("L'utilisateur ne peut pas être null.");
        }

        if (alert.getCryptoCurrency() == null ||
                (alert.getPriceThreshold() == null && alert.getVariationThreshold() == null)) {
            throw new IllegalArgumentException("L'alerte doit avoir une crypto-monnaie et au moins un seuil défini.");
        }

        List<Alerts> existingAlerts = findByUser(user);

        for (Alerts existingAlert : existingAlerts) {
            if (existingAlert.getCryptoCurrency().equals(alert.getCryptoCurrency()) &&
                    Objects.equals(existingAlert.getPriceThreshold(), alert.getPriceThreshold()) &&
                    Objects.equals(existingAlert.getVariationThreshold(), alert.getVariationThreshold())) {
                throw new IllegalStateException("Une alerte similaire existe déjà pour cet utilisateur.");
            }
        }

        if (checkNombreAlerts(user)) {
            throw new IllegalStateException("Vous avez atteint le nombre maximum d'alertes., Passez à un abonnement Premium.");
        }

        alert.setUser(user);
        alert.setName(alert.getName());
        alert.setCryptoCurrency(alert.getCryptoCurrency());
        alert.setPriceThreshold(alert.getPriceThreshold());
        alert.setVariationThreshold(alert.getVariationThreshold());

        System.out.println("creation is done");
        return alertsRepository.save(alert);
    }

    public boolean checkNombreAlerts(User user) {
        List<Alerts> alerts = findByUser(user);
        return alerts.size() == 10;
    }

    /**
     * Updates an existing alert for the given user.
     *
     * @param alertId the ID of the alert to update
     * @param user the user who owns the alert
     * @param updatedAlert the updated alert object
     * @return the updated alert
     */
    public Alerts updateAlert(Long alertId, User user, Alerts updatedAlert) {
        Alerts existingAlert = alertsRepository.findById(alertId)
                .orElseThrow(() -> new RuntimeException("Alerte introuvable"));

        if (!existingAlert.getUser().equals(user)) {
            throw new IllegalStateException("Vous n'êtes pas autorisé à modifier cette alerte.");
        }

        existingAlert.setName(updatedAlert.getName());
        existingAlert.setPriceThreshold(updatedAlert.getPriceThreshold());
        existingAlert.setVariationThreshold(updatedAlert.getVariationThreshold());
        existingAlert.setCryptoCurrency(updatedAlert.getCryptoCurrency());

        System.out.println("update is done");
        return alertsRepository.save(existingAlert);
    }

    /**
     * Deletes an alert with the given ID for the given user.
     *
     * @param alertId the ID of the alert to delete
     * @param user the user who owns the alert
     *
     * @throws RuntimeException if the alert is not found
     * @throws IllegalStateException if the user is not authorized to delete the alert
     */
    public void deleteAlert(Long alertId, User user) {
        Alerts alert = alertsRepository.findById(alertId)
                .orElseThrow(() -> new RuntimeException("Alerte introuvable"));

        if (!alert.getUser().equals(user)) {
            throw new IllegalStateException("Vous n'êtes pas autorisé à supprimer cette alerte.");
        }

        System.out.println("delete is done");
        alertsRepository.delete(alert);
    }



}

