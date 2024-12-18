package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.Alerts;
import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.model.MarketData;
import com.cryptocurrency.data.model.User;
import com.cryptocurrency.data.repository.AlertsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
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

    /**
     * The alertsService field is an instance of the AlertsService class.
     */
    @InjectMocks
    private AlertsService alertsService;

    /**
     * The marketData field is an instance of the MarketData class.
     */
    private MarketData marketData;

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
     * Creates a MarketData object, a User object and two Alerts objects.
     */
    @BeforeEach
    public void setUp() {
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        marketData = new MarketData(1L, cryptoCurrency, LocalDateTime.now(), 1.0, 1.0, 1.0);
        user = new User(1L, "user1",  "email1", "tokenHash1", "passwordHash1");

        alerts1 = new Alerts(1L, user, marketData, 1.0, 2.0);
        alerts2 = new Alerts(2L, user, marketData, 1.0, 2.0);
    }

    /**
     * Test the findByUser() method of the AlertsService class.
     * This test verifies that the findByUser() method returns the correct list of alerts
     * when given a valid User object.
     */
    @Test
    public void testFindByUser() {
        when(alertsRepository.findByUser(user)).thenReturn(List.of(alerts1, alerts2));
        List<Alerts> result = alertsService.findByUser(user);

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(alertsRepository, times(1)).findByUser(user);
    }

    /**
     * Test the findByMarketData() method of the AlertsService class.
     * This test verifies that the findByMarketData() method returns the correct list of alerts
     * when given a valid MarketData object.
     */
    @Test
    public void testFindByMarketData() {
        when(alertsRepository.findByMarketData(marketData)).thenReturn(List.of(alerts1, alerts2));
        List<Alerts> result = alertsService.findByMarketData(marketData);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());

        verify(alertsRepository, times(1)).findByMarketData(marketData);
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

    /**
     * Test the findByMarketDataAndUser() method of the AlertsService class.
     * This test verifies that the findByMarketDataAndUser() method returns the correct list of alerts
     * when given a valid MarketData object and a valid User object.
     */
    @Test
    public void testFindByCryptoCurrencyAndUser() {
        when(alertsRepository.findByMarketDataAndUser(marketData, user)).thenReturn(List.of(alerts1, alerts2));
        List<Alerts> result = alertsService.findByMarketDataAndUser(marketData, user);

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(alertsRepository, times(1)).findByMarketDataAndUser(marketData, user);
    }
}
