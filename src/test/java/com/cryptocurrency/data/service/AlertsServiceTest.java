package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.Alerts;
import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.model.User;
import com.cryptocurrency.data.repository.AlertsRepository;

import com.cryptocurrency.data.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * The AlertsServiceTest class is a JUnit test class for the AlertsService class.
 * Author: Mouhamadou Ahibou DIALLO
 */
@ExtendWith(MockitoExtension.class)
public class AlertsServiceTest {

    /**
     * The alertsRepository field is a mock of the AlertsRepository interface.
     */
    @Mock
    private AlertsRepository alertsRepository;

    @Mock
    private UserRepository userRepository;

    /**
     * The alertsService field is an instance of the AlertsService class.
     */
    @InjectMocks
    private AlertsService alertsService;

    /**
     * The user field is an instance of the User class.
     */
    private User user;

    /**
     * The alerts1 field is an instance of the Alerts class.
     */
    private Alerts alerts1;

    /**
     * The alerts2 field is an instance of the Alerts class.
     */
    private Alerts alerts2;


    /**
     * Sets up the test environment before each test.
     * Creates a cryptoCurrency object, a User object and two Alerts objects.
     */
    @BeforeEach
    public void setUp() {
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        user = new User(1L, "user1",  "email1", "tokenHash1", "passwordHash1");

        alerts1 = new Alerts(1L, user, cryptoCurrency, 1.0, 2.0);
        alerts2 = new Alerts(2L, user, cryptoCurrency, 1.0, 2.0);
    }

    /**
     * Test the findByUser() method of the AlertsService class.
     * This test verifies that the findByUser() method returns the correct list of alerts
     * when given a valid User object.
     */
    @Test
    public void testFindByUser() {
        when(alertsRepository.findByUser(user)).thenReturn(List.of(alerts1, alerts2));
        //when(userRepository.findById(user.getId())).thenReturn(user);
        List<Alerts> result = alertsService.findByUser(user);

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(alertsRepository, times(1)).findByUser(user);
    }

    /**
     * Test the save() method of the AlertsService class.
     * This test verifies that the save() method returns the correct alerts object
     * and that the save() method is called once.
     */
    @Test
    public void testSave() {
        when(alertsRepository.save(alerts1)).thenReturn(alerts1);
        Alerts result = alertsService.save(alerts1);

        assertEquals(1L, result.getId(), "The ID of the saved alert should be 1");
        verify(alertsRepository, times(1)).save(alerts1);
    }

    /**
     * Test the deleteById() method of the AlertsService class.
     * This test verifies that the deleteById() method is called once with the correct ID.
     */
    @Test
    public void testDeleteById() {
        alertsService.deleteById(1L);
        verify(alertsRepository, times(1)).deleteById(1L);
    }

    /**
     * Test the findById() method of the AlertsService class.
     * This test verifies that the findById() method returns the correct alerts object
     * when given a valid id.
     */
    @Test
    public void testFindById() {
        when(alertsRepository.findById(1L)).thenReturn(java.util.Optional.of(alerts1));
        Alerts result = alertsService.findById(1L);

        assertEquals(1L, result.getId(), "The ID of the saved alert should be 1");
        verify(alertsRepository, times(1)).findById(1L);
    }

    /**
     * Test the findAll() method of the AlertsService class.
     * This test verifies that the findAll() method returns the correct list of alerts.
     */
    @Test
    public void testFindAll() {
        when(alertsRepository.findAll()).thenReturn(List.of(alerts1, alerts2));
        List<Alerts> result = alertsService.findAll();

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());

        verify(alertsRepository, times(1)).findAll();
    }

    /**
     * Test the findByPriceThreshold() method of the AlertsService class.
     * This test verifies that the findByPriceThreshold() method returns the correct list of alerts
     * when given a valid price threshold.
     */
    @Test
    public void testFindByPriceThreshold() {
        when(alertsRepository.findByPriceThreshold(1.0)).thenReturn(List.of(alerts1, alerts2));
        List<Alerts> result = alertsService.findByPriceThreshold(1.0);

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());

        verify(alertsRepository, times(1)).findByPriceThreshold(1.0);
    }

    /**
     * Test the findByVariationThreshold() method of the AlertsService class.
     * This test verifies that the findByVariationThreshold() method returns the correct list of alerts
     * when given a valid variation threshold.
     */
    @Test
    public void testFindByVariationThreshold() {

        when(alertsRepository.findByVariationThreshold(1.0)).thenReturn(List.of(alerts1, alerts2));
        List<Alerts> result = alertsService.findByVariationThreshold(1.0);

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(alertsRepository, times(1)).findByVariationThreshold(1.0);
    }
    /**
     * Test the deleteByUser() method of the AlertsService class.
     * This test verifies that the deleteByUser() method calls the deleteByUser() method of the AlertsRepository class once
     * with the correct User object.
     */

    @Test
    public void testDeleteByUser() {
        alertsService.deleteByUser(user);
        verify(alertsRepository, times(1)).deleteByUser(user);
    }
}
