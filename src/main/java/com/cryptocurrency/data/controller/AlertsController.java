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
@RequestMapping("/alerts")
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
     *
     * @param user   the user whose alert is to be created
     * @param alert  the alert to be created
     * @return the created alert
     */
    @PostMapping("/users/{user}/alerts")
    public ResponseEntity<Alerts> createAlert(
            @PathVariable("user") User user,
            @RequestBody Alerts alert) {
        Alerts createdAlert = alertsService.createAlert(user, alert);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAlert);
    }

    /**
     * Updates an existing alert with the given ID.
     *
     * @param id   the ID of the alert to be updated
     * @param alert the updated alert object
     * @return the updated alert
     */
    @PutMapping("/{id}")
    public ResponseEntity<Alerts> updateAlert(@PathVariable Long id, @RequestBody Alerts alert) {
        Alerts existingAlert = alertsService.findById(id);

        if (existingAlert != null) {
            alert.setId(id);
            return ResponseEntity.ok(alertsService.save(alert));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes an alert with the given ID.
     *
     * @param id the ID of the alert to be deleted
     * @return a response entity indicating the deletion was successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlert(@PathVariable Long id) {
        alertsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
