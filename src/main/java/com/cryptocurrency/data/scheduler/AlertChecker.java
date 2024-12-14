package com.cryptocurrency.data.scheduler;

import com.cryptocurrency.data.service.AlertsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AlertChecker {

    @Autowired
    private AlertsService alertsService;

    @Scheduled(fixedRate = 60000)
    public void checkAlerts() {
        alertsService.checkAlerts();
    }
}
