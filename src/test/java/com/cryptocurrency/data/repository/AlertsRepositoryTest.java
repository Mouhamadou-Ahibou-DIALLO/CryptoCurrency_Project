package com.cryptocurrency.data.repository;

import com.cryptocurrency.data.model.Alerts;
import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class AlertsRepositoryTest {

    @Mock
    private AlertsRepository alertsRepository;
    private CryptoCurrency cryptoCurrency;
    private User user;

    private Alerts alerts1;
    private Alerts alerts2;
    private Alerts alerts3;
    private Alerts alerts4;

    @BeforeEach
    public void setUp() {
        cryptoCurrency = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        user = new User(1L, "user1",  "email1", "tokenHash1", "passwordHash1");
        alerts1 = new Alerts(1L, user, cryptoCurrency, 1.0, 1.0);
        alerts2 = new Alerts(2L, user, cryptoCurrency, 2.0, 2.0);
        alerts3 = new Alerts(3L, user, cryptoCurrency, 3.0, 3.0);
        alerts4 = new Alerts(4L, user, cryptoCurrency, 1.0, 1.0);
    }

    @Test
    public void testFindAll() {
        when(alertsRepository.findAll()).thenReturn(List.of(alerts1, alerts2, alerts3));
        List<Alerts> result = alertsRepository.findAll();
        assertEquals(3, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(alertsRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        when(alertsRepository.save(alerts1)).thenReturn(alerts1);
        Alerts result = alertsRepository.save(alerts1);
        assertEquals(1L, result.getId());
        verify(alertsRepository, times(1)).save(alerts1);
    }

    @Test
    public void testDeleteById() {
        alertsRepository.deleteById(1L);
        verify(alertsRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testFindById() {
        when(alertsRepository.findById(1L)).thenReturn(java.util.Optional.of(alerts1));
        Alerts result = alertsRepository.findById(1L).get();
        assertEquals(1L, result.getId());
        verify(alertsRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindByCryptoCurrency() {
        when(alertsRepository.findByCryptoCurrency(cryptoCurrency)).thenReturn(List.of(alerts1, alerts2, alerts3));
        List<Alerts> result = alertsRepository.findByCryptoCurrency(cryptoCurrency);
        assertEquals(3, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(alertsRepository, times(1)).findByCryptoCurrency(cryptoCurrency);
    }

    @Test
    public void testFindByUser() {
        when(alertsRepository.findByUser(user)).thenReturn(List.of(alerts1, alerts2, alerts3));
        List<Alerts> result = alertsRepository.findByUser(user);
        assertEquals(3, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(alertsRepository, times(1)).findByUser(user);
    }

    @Test
    public void testFindByCryptoCurrencyAndUser() {
        when(alertsRepository.findByCryptoCurrencyAndUser(cryptoCurrency, user)).thenReturn(List.of(alerts1, alerts2, alerts3));
        List<Alerts> result = alertsRepository.findByCryptoCurrencyAndUser(cryptoCurrency, user);
        assertEquals(3, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(alertsRepository, times(1)).findByCryptoCurrencyAndUser(cryptoCurrency, user);
    }

    @Test
    public void testFindByPriceThreshold() {
        when(alertsRepository.findByPriceThreshold(1.0)).thenReturn(List.of(alerts1, alerts4));
        List<Alerts> result = alertsRepository.findByPriceThreshold(1.0);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(4L, result.get(1).getId());
        verify(alertsRepository, times(1)).findByPriceThreshold(1.0);
    }

    @Test
    public void testFindByVariationThreshold() {
        when(alertsRepository.findByVariationThreshold(1.0)).thenReturn(List.of(alerts1, alerts4));
        List<Alerts> result = alertsRepository.findByVariationThreshold(1.0);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(4L, result.get(1).getId());
        verify(alertsRepository, times(1)).findByVariationThreshold(1.0);
    }
}
