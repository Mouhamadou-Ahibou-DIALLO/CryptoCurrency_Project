package com.cryptocurrency.data.controller;

import com.cryptocurrency.data.model.Alerts;
import com.cryptocurrency.data.model.User;
import com.cryptocurrency.data.service.AlertsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The AlertsController class is a Spring REST controller for managing alerts.
 * Author: Mouhamadou Ahibou DIALLO
 */
@RestController
@RequestMapping("/api/alerts")
public class AlertsController {

    /**
     * The alertsService field is a Spring service for managing alerts.
     */
    @Autowired
    private AlertsService alertsService;

    /**
     * Gets all alerts for a given user.
     *
     * @param user the user whose alerts are to be retrieved
     * @return a list of alerts for the given user
     */
    @GetMapping
    public ResponseEntity<List<Alerts>> getAlertsByUser(@RequestParam User user) {
        List<Alerts> alerts = alertsService.findByUser(user);
        return ResponseEntity.ok(alerts);
    }

    /**
     * Creates a new alert for the given user.
     * @param alert  the alert to be created
     * @return the created alert
     */
    @PostMapping("/create")
    public ResponseEntity<String> createAlert(@RequestBody Alerts alert) {
        try {
            User user = alert.getUser();
            if (user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'utilisateur ne peut pas быть null.");
            }

            if (alert.getCryptoCurrency() == null ||
                    (alert.getPriceThreshold() == null && alert.getVariationThreshold() == null)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'alerte doit avoir une crypto-monnaie et au moins un seuil definit.");
            }

            Alerts createdAlert = alertsService.createAlert(user, alert);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAlert.getName());
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("Passez à un abonnement Premium")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Updates an existing alert with the given ID.
     *
     * @param id   the ID of the alert to be updated
     * @param updatedAlert the updated alert object
     * @return the updated alert
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAlert(@PathVariable Long id, @RequestBody Alerts updatedAlert) {
        try {
            User user = updatedAlert.getUser();
            if (user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'utilisateur ne peut pas être null.");
            }

            Alerts updated = alertsService.updateAlert(id, user, updatedAlert);
            return ResponseEntity.ok(updated.getName() + " updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Deletes an alert with the given ID.
     *
     * @param id the ID of the alert to be deleted
     * @return a response entity indicating the deletion was successful
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAlert(@PathVariable Long id, @RequestParam Alerts alertDeleted) {
        try {
            User user = alertDeleted.getUser();
            if (user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'utilisateur ne peut pas être null.");
            }

            alertsService.deleteAlert(id, user);
            return ResponseEntity.ok("Alert deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
