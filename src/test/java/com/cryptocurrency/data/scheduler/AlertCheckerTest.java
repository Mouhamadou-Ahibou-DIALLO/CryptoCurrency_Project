package com.cryptocurrency.data.scheduler;

import com.cryptocurrency.data.service.AlertsService;

import com.cryptocurrency.data.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.scheduling.annotation.Scheduled;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * The AlertCheckerTest class is a JUnit test class for the AlertChecker class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class AlertCheckerTest {

    /**
     * The alertsService.
     */
    @InjectMocks
    private AlertsService alertsService;

    /**
     * The transactionService.
     */
    @InjectMocks
    private TransactionService transactionService;

    /**
     * The alertChecker.
     */
    private AlertChecker alertChecker;

    /**
     * The setUp method is used to initialize the alertsService and transactionService and alertChecker objects
     * before each test.
     */
    @BeforeEach
    public void setUp() {
        alertsService = Mockito.mock(AlertsService.class);
        transactionService = Mockito.mock(TransactionService.class);
        alertChecker = new AlertChecker(alertsService, transactionService);
    }

    /**
     * The testCheckAlerts method tests the checkAlerts() method of the AlertChecker class.
     * And it verifies that the checkAlerts() method of the AlertsService class is called once.
     * This test verifies that the checkAlerts() method invokes the checkAlerts() method
     * of the AlertsService class.
     */
    @Test
    public void testCheckAlerts() {
        alertChecker.checkAlerts();
        verify(alertsService, times(1)).checkAlerts();
        verify(transactionService, times(1)).checkAlert();
    }

    /**
     * Test to verify that the checkAlerts method of the AlertChecker class
     * And it verifies that the checkAlerts method of the AlertsService class is called once.
     * invokes the checkAlerts method of the AlertsService class exactly once.
     */
    @Test
    void checkAlerts_ShouldInvokeServiceMethod() {
        alertChecker.checkAlerts();
        verify(alertsService, times(1)).checkAlerts();
        verify(transactionService, times(1)).checkAlert();
    }

    /**
     * The testCheckAlerts_ShouldHaveScheduledAnnotation method tests the checkAlerts() method of the AlertChecker class.
     * This test verifies that the checkAlerts() method has the @Scheduled annotation with the correct fixed rate.
     */
    @Test
    void checkAlerts_ShouldHaveScheduledAnnotation() throws NoSuchMethodException {
        Scheduled scheduledAnnotation = AlertChecker.class
                .getMethod("checkAlerts")
                .getAnnotation(Scheduled.class);

        assert scheduledAnnotation != null;
        assert scheduledAnnotation.fixedRate() == 60000;
    }

}
