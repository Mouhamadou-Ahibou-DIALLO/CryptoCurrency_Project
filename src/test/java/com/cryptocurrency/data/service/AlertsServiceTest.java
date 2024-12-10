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

@ExtendWith(MockitoExtension.class)
public class AlertsServiceTest {

    @Mock
    private AlertsRepository alertsRepository;

    @InjectMocks
    private AlertsService alertsService;

    private CryptoCurrency cryptoCurrency;
    private User user;

    private Alerts alerts1;
    private Alerts alerts2;


    @BeforeEach
    public void setUp() {
        cryptoCurrency = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        user = new User(1L, "user1",  "email1", "tokenHash1", "passwordHash1");
        alerts1 = new Alerts(1L, user, cryptoCurrency, 1.0, 2.0);
        alerts2 = new Alerts(2L, user, cryptoCurrency, 1.0, 2.0);
    }

    @Test
    public void testFindByUser() {
        when(alertsRepository.findByUser(user)).thenReturn(List.of(alerts1, alerts2));
        List<Alerts> result = alertsService.findByUser(user);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(alertsRepository, times(1)).findByUser(user);
    }

    @Test
    public void testFindByCryptoCurrency() {
        when(alertsRepository.findByCryptoCurrency(cryptoCurrency)).thenReturn(List.of(alerts1, alerts2));
        List<Alerts> result = alertsService.findByCryptoCurrency(cryptoCurrency);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(alertsRepository, times(1)).findByCryptoCurrency(cryptoCurrency);
    }

    @Test
    public void testSave() {
        when(alertsRepository.save(alerts1)).thenReturn(alerts1);
        Alerts result = alertsService.save(alerts1);
        assertEquals(1L, result.getId());
        verify(alertsRepository, times(1)).save(alerts1);
    }

    @Test
    public void testDeleteById() {
        alertsService.deleteById(1L);
        verify(alertsRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testFindById() {
        when(alertsRepository.findById(1L)).thenReturn(java.util.Optional.of(alerts1));
        Alerts result = alertsService.findById(1L);
        assertEquals(1L, result.getId());
        verify(alertsRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindAll() {
        when(alertsRepository.findAll()).thenReturn(List.of(alerts1, alerts2));
        List<Alerts> result = alertsService.findAll();
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(alertsRepository, times(1)).findAll();
    }

    @Test
    public void testFindByPriceThreshold() {
        when(alertsRepository.findByPriceThreshold(1.0)).thenReturn(List.of(alerts1, alerts2));
        List<Alerts> result = alertsService.findByPriceThreshold(1.0);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(alertsRepository, times(1)).findByPriceThreshold(1.0);
    }

    @Test
    public void testFindByVariationThreshold() {
        when(alertsRepository.findByVariationThreshold(1.0)).thenReturn(List.of(alerts1, alerts2));
        List<Alerts> result = alertsService.findByVariationThreshold(1.0);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(alertsRepository, times(1)).findByVariationThreshold(1.0);
    }

    @Test
    public void testDeleteByUser() {
        alertsService.deleteByUser(user);
        verify(alertsRepository, times(1)).deleteByUser(user);
    }

    @Test
    public void testFindByCryptoCurrencyAndUser() {
        when(alertsRepository.findByCryptoCurrencyAndUser(cryptoCurrency, user)).thenReturn(List.of(alerts1, alerts2));
        List<Alerts> result = alertsService.findByCryptoCurrencyAndUser(cryptoCurrency, user);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(alertsRepository, times(1)).findByCryptoCurrencyAndUser(cryptoCurrency, user);
    }
}
