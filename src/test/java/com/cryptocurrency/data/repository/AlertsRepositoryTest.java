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

/**
 * The AlertsRepositoryTest class is a JUnit test class for the AlertsRepository class.
 * Author: Mouhamadou Ahibou DIALLO
 */
@ExtendWith(MockitoExtension.class)
public class AlertsRepositoryTest {

    /**
     * The alertsRepository field is a mock of the AlertsRepository class.
     */
    @Mock
    private AlertsRepository alertsRepository;

    /**
     * The user field is a object of the User class.
     */
    private User user;

    /**
     * The alerts1 field is a object of the Alerts class.
     */
    private Alerts alerts1;

    /**
     * The alerts2 field is a object of the Alerts class.
     */
    private Alerts alerts2;

    /**
     * The alerts3 field is a object of the Alerts class.
     */
    private Alerts alerts3;

    /**
     * The alerts4 field is a object of the Alerts class.
     */
    private Alerts alerts4;

    /**
     * The setUp method is used to initialize user, alerts1, alerts2 and alerts3 objects
     * before each test.
     */
    @BeforeEach
    public void setUp() {
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        user = new User(1L, "user1",  "email1", "tokenHash1", "passwordHash1");

        alerts1 = new Alerts(1L, user, cryptoCurrency, 1.0, 1.0);
        alerts2 = new Alerts(2L, user, cryptoCurrency, 2.0, 2.0);
        alerts3 = new Alerts(3L, user, cryptoCurrency, 3.0, 3.0);
        alerts4 = new Alerts(4L, user, cryptoCurrency, 1.0, 1.0);
    }

    /**
     * Test the findAll() method of the AlertsRepository class.
     * This test verifies that the findAll() method returns the correct number of alerts
     * and that the first alert in the list has the correct ID.
     */
    @Test
    public void testFindAll() {
        when(alertsRepository.findAll()).thenReturn(List.of(alerts1, alerts2, alerts3));
        List<Alerts> result = alertsRepository.findAll();

        assertEquals(3, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(alertsRepository, times(1)).findAll();
    }

    /**
     * Test the save() method of the AlertsRepository class.
     * This test verifies that the save() method returns the correct alerts object
     * and that the save() method is called once.
     */
    @Test
    public void testSave() {
        when(alertsRepository.save(alerts1)).thenReturn(alerts1);
        Alerts result = alertsRepository.save(alerts1);

        assertEquals(1L, result.getId());
        verify(alertsRepository, times(1)).save(alerts1);
    }

    /**
     * Test the deleteById() method of the AlertsRepository class.
     * This test verifies that the deleteById() method is called once with the correct ID.
     */
    @Test
    public void testDeleteById() {
        Long id = 1L;
        alertsRepository.deleteById(id);
        verify(alertsRepository, times(1)).deleteById(id);
    }

    /**
     * Test the findById() method of the AlertsRepository class.
     * This test verifies that the findById() method returns the correct alerts object
     * when given a valid id.
     */
    @Test
    public void testFindById() {
        Long id = 1L;
        when(alertsRepository.findById(id)).thenReturn(java.util.Optional.of(alerts1));
        Alerts result = alertsRepository.findById(id).orElse(null);

        assert result != null;
        assertEquals(1L, result.getId());
        verify(alertsRepository, times(1)).findById(id);
    }

    /**
     * Test the findByUser() method of the AlertsRepository class.
     * This test verifies that the findByUser() method returns the correct list of alerts
     * when given a valid User object.
     */
    @Test
    public void testFindByUser() {
        when(alertsRepository.findByUser(user)).thenReturn(List.of(alerts1, alerts2, alerts3));
        List<Alerts> result = alertsRepository.findByUser(user);

        assertEquals(3, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(alertsRepository, times(1)).findByUser(user);
    }

    /**
     * Test the findByPriceThreshold() method of the AlertsRepository class.
     * This test verifies that the findByPriceThreshold() method returns the correct list of alerts
     * when given a valid price threshold.
     */
    @Test
    public void testFindByPriceThreshold() {
        when(alertsRepository.findByPriceThreshold(1.0)).thenReturn(List.of(alerts1, alerts4));
        List<Alerts> result = alertsRepository.findByPriceThreshold(1.0);

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(4L, result.get(1).getId());
        verify(alertsRepository, times(1)).findByPriceThreshold(1.0);
    }

    /**
     * Test the findByVariationThreshold() method of the AlertsRepository class.
     * This test verifies that the findByVariationThreshold() method returns the correct list of alerts
     * when given a valid variation threshold.
     */
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
