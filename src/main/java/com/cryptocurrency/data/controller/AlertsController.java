package com.cryptocurrency.data.controller;

import com.cryptocurrency.data.model.Alerts;
import com.cryptocurrency.data.model.User;
import com.cryptocurrency.data.service.AlertsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertsController {

    @Autowired
    private AlertsService alertsService;

    @GetMapping
    public ResponseEntity<List<Alerts>> getAlertsByUser(User user) {
        List<Alerts> alerts = alertsService.findByUser(user);
        return ResponseEntity.ok(alerts);
    }

    @PostMapping
    public ResponseEntity<Alerts> createAlert(@RequestBody Alerts alert) {
        Alerts savedAlert = alertsService.save(alert);
        return ResponseEntity.ok(savedAlert);
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlert(@PathVariable Long id) {
        alertsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
