package com.cryptocurrency.data.controller;

import com.cryptocurrency.data.model.Alerts;
import com.cryptocurrency.data.model.User;
import com.cryptocurrency.data.repository.UserRepository;
import com.cryptocurrency.data.service.AlertCreatedService;
import com.cryptocurrency.data.service.AlertUpdateService;
import com.cryptocurrency.data.service.AlertsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The AlertsControllerTest class is a JUnit test class for the AlertsController class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class AlertsControllerTest {

    /** The alertsService field is a mock of the AlertsService class. */
    @Mock
    private AlertsService alertsService;

    /** The userRepository field is a mock of the UserRepository class. */
    @Mock
    private UserRepository userRepository;

    /** The alertsController field is an instance of the AlertsController class. */
    @InjectMocks
    private AlertsController alertsController;

    /**
     * Sets up the test environment before each test.
     * <p>
     * This method initializes the Mockito annotations and injects the mock objects
     * into the AlertsController instance.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the getAlertsByUser() method of the AlertsController class.
     * This test verifies that the getAlertsByUser() method returns a list of alerts for
     * a given user and that the findByUser() method is called once with the correct user.
     */
    @Test
    public void testGetAlertsByUser_Success() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        Alerts alert = new Alerts();
        alert.setId(1L);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(alertsService.findByUser(user)).thenReturn(List.of(alert));
        ResponseEntity<List<Alerts>> response = alertsController.getAlertsByUser(userId);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());

        verify(userRepository).findById(userId);
        verify(alertsService).findByUser(user);
    }

    /**
     * Tests the getAlertsByUser() method of the AlertsController class when the
     * findByUser() method returns an empty list.
     * This test verifies that the getAlertsByUser() method returns a 204 (no content)
     * response and that the findByUser() method is called once with the correct user.
     */
    @Test
    public void testGetAlertsByUser_EmptyList() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(alertsService.findByUser(user)).thenReturn(Collections.emptyList());
        ResponseEntity<List<Alerts>> response = alertsController.getAlertsByUser(userId);

        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
        assertNull(response.getBody());

        verify(userRepository).findById(userId);
        verify(alertsService).findByUser(user);
    }

    /**
     * Tests the createAlert() method of the AlertsController class.
     * This test verifies that the createAlert() method successfully creates a new alert
     * and returns a response with HTTP status 201 (Created). It also checks that the
     * response body contains the created alert mapped by its name.
     */
    @Test
    public void testCreateAlert_Success() {
        AlertCreatedService alertRequest = new AlertCreatedService();
        Alerts alert = new Alerts();
        alert.setName("Test Alert");

        when(alertsService.createAlert(alertRequest)).thenReturn(alert);
        ResponseEntity<?> response = alertsController.createAlert(alertRequest);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertInstanceOf(Map.class, response.getBody());

        Map<?, ?> responseBody = (Map<?, ?>) response.getBody();
        assertEquals(alert, responseBody.get("Test Alert"));
        verify(alertsService).createAlert(alertRequest);
    }

    /**
     * Tests the updateAlert() method of the AlertsController class when the update
     * operation is successful.
     * This test verifies that the updateAlert() method successfully updates an existing
     * alert and returns a response with HTTP status 200 (OK). It also checks that the
     * response body contains the updated alert mapped by its name.
     */
    @Test
    public void testUpdateAlert_Success() {
        Long alertId = 1L;
        AlertUpdateService alertUpdateRequest = new AlertUpdateService();

        User user = new User();
        user.setId(1L);

        Alerts alert = new Alerts();
        alert.setUser(user);
        alert.setName("Updated Alert");

        when(alertsService.findById(alertId)).thenReturn(alert);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(alertsService.updateAlert(alertId, user, alertUpdateRequest)).thenReturn(alert);

        ResponseEntity<?> response = alertsController.updateAlert(alertId, alertUpdateRequest);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertInstanceOf(Map.class, response.getBody());

        Map<?, ?> responseBody = (Map<?, ?>) response.getBody();
        assertEquals(alert, responseBody.get("Updated Alert"));

        verify(alertsService).findById(alertId);
        verify(userRepository).findById(user.getId());
        verify(alertsService).updateAlert(alertId, user, alertUpdateRequest);
    }

    /**
     * Tests the deleteAlert() method of the AlertsController class when the delete
     * operation is successful.
     * This test verifies that the deleteAlert() method successfully deletes an alert
     * and returns a response with HTTP status 200 (OK). It also checks that the
     * response body contains the IDs of the deleted alert and user.
     */
    @Test
    public void testDeleteAlert_Success() {
        Long alertId = 1L;
        Long userId = 1L;

        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        ResponseEntity<?> response = alertsController.deleteAlert(alertId, userId);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertInstanceOf(Map.class, response.getBody());

        Map<?, ?> responseBody = (Map<?, ?>) response.getBody();
        assertEquals(alertId, responseBody.get("alertId"));
        assertEquals(userId, responseBody.get("userId"));

        verify(userRepository).findById(userId);
        verify(alertsService).deleteAlert(alertId, user);
    }
}

