package com.cryptocurrency.data.scheduler;

import com.cryptocurrency.data.service.AlertsService;

import com.cryptocurrency.data.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * The AlertChecker class is a Spring Boot scheduled task that checks for alerts.
 * Author: Mouhamadou Ahibou DIALLO
 */
@Component
public class AlertChecker {

    /**
     * The alertsService field is an instance of the AlertsService class.
     */
    @Autowired
    private AlertsService alertsService;

    @Autowired
    private TransactionService transactionService;

    /**
     * The constructor for the AlertChecker class.
     *
     *  @param alertsService The alertsService field is an instance of the AlertsService class.
     */
    public AlertChecker(AlertsService alertsService) {
        this.alertsService = alertsService;
    }

    /**
     * The constructor for the AlertChecker class.
     */
    public AlertChecker() {}

    /**
     * This method is a scheduled task that runs every minute to check for alerts.
     * It delegates the task to the alertsService.
     */
    @Scheduled(fixedRate = 60000)
    public void checkAlerts() {
        alertsService.checkAlerts();
    }
}
