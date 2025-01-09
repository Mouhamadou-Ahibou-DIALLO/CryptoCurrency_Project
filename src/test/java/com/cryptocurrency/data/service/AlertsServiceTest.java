package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.Alerts;
import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.model.User;
import com.cryptocurrency.data.repository.AlertsRepository;

import com.cryptocurrency.data.repository.CryptoCurrencyRepository;
import com.cryptocurrency.data.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
     * The userRepository field is a mock of the UserRepository interface.
     */
    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailService emailService;

    /**
     * The cryptoCurrencyRepository field is a mock of the CryptoCurrencyRepository interface.
     */
    @Mock
    private CryptoCurrencyRepository cryptoCurrencyRepository;

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
     * The cryptoCurrency field is an instance of the CryptoCurrency class.
     */
    private CryptoCurrency cryptoCurrency;

    /**
     * The mockUser field is a mock of the User class.
     */
    private User mockUser;

    /**
     * The mockCryptoCurrency field is a mock of the CryptoCurrency class.
     */
    private CryptoCurrency mockCryptoCurrency;

    /**
     * The mockAlert field is a mock of the Alerts class.
     */
    private Alerts mockAlert;


    /**
     * Sets up the test environment before each test.
     * Creates a cryptoCurrency object, a User object and two Alerts objects.
     */
    @BeforeEach
    public void setUp() {
        JavaMailSender javaMailSender = mock(JavaMailSender.class);
        emailService = new EmailService(javaMailSender);
        alertsService = new AlertsService(alertsRepository, emailService, userRepository, cryptoCurrencyRepository);

        cryptoCurrency = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        user = new User(1L, "user1",  "email1", "tokenHash1", "passwordHash1");

        alerts1 = new Alerts(1L, user, cryptoCurrency, 55000.0, 3.0);
        alerts2 = new Alerts(2L, user, cryptoCurrency, 62000.0, 6.0);

        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setStatut("normal");

        mockCryptoCurrency = new CryptoCurrency();
        mockCryptoCurrency.setId(1L);

        mockAlert = new Alerts();
        mockAlert.setId(1L);
        mockAlert.setUser(mockUser);
        mockAlert.setCryptoCurrency(mockCryptoCurrency);
        mockAlert.setName("Bitcoin Alert");
        mockAlert.setPriceThreshold(50000.0);
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
     * Test the checkAlerts() method of the AlertsService class.
     * This test verifies that the checkAlerts() method calls the findAll() method of the AlertsRepository class once
     * and that the checkAlerts() method of the AlertsService class does not throw any exceptions.
     */
    @Test
    public void testCheckAlerts() {
        when(alertsRepository.findAll()).thenReturn(Arrays.asList(alerts1, alerts2));
        cryptoCurrency.setPrice(60000.0);
        cryptoCurrency.setChange(5.0);

        alertsService.checkAlerts();
        verify(alertsRepository, times(1)).findAll();
    }

    /**
     * Test the createAlert() method of the AlertsService class.
     * This test verifies that the createAlert() method successfully creates a new alert
     * and returns a response with HTTP status 201 (Created). It also checks that the
     * response body contains the created alert mapped by its name.
     */
    @Test
    public void testCreateAlert_Success() {
        AlertCreatedService alertCreatedService = new AlertCreatedService();
        alertCreatedService.setUser(mockUser);
        alertCreatedService.setCryptoCurrency(mockCryptoCurrency);
        alertCreatedService.setPriceThreshold(50000.0);
        alertCreatedService.setVariationThreshold(5.0);
        alertCreatedService.setName("Bitcoin Alert");

        when(userRepository.findById(mockUser.getId())).thenReturn(Optional.of(mockUser));
        when(cryptoCurrencyRepository.findById(mockCryptoCurrency.getId())).thenReturn(Optional.of(mockCryptoCurrency));
        when(alertsRepository.save(any(Alerts.class))).thenReturn(mockAlert);

        Alerts createdAlert = alertsService.createAlert(alertCreatedService);

        assertNotNull(createdAlert);
        assertEquals("Bitcoin Alert", createdAlert.getName());
        verify(alertsRepository, times(1)).save(any(Alerts.class));
    }

    /**
     * Test the updateAlert() method of the AlertsService class when the update
     * operation is successful.
     * This test verifies that the updateAlert() method successfully updates an existing
     * alert and returns a response with HTTP status 200 (OK). It also checks that the
     * response body contains the updated alert mapped by its name.
     */
    @Test
    public void testUpdateAlert_Success() {
        AlertUpdateService alertUpdateService = new AlertUpdateService();
        alertUpdateService.setName("Updated Bitcoin Alert");
        alertUpdateService.setPriceThreshold(60000.0);
        alertUpdateService.setVariationThreshold(5.0);

        when(alertsRepository.findById(mockAlert.getId())).thenReturn(Optional.of(mockAlert));
        when(userRepository.findById(mockUser.getId())).thenReturn(Optional.of(mockUser));
        when(alertsRepository.save(any(Alerts.class))).thenReturn(mockAlert);

        Alerts updatedAlert = alertsService.updateAlert(mockAlert.getId(), mockUser, alertUpdateService);

        assertNotNull(updatedAlert);
        assertEquals("Updated Bitcoin Alert", updatedAlert.getName());
        assertEquals(60000.0, updatedAlert.getPriceThreshold());
        verify(alertsRepository, times(1)).save(any(Alerts.class));
    }

    /**
     * Test the deleteAlert() method of the AlertsService class when the delete
     * operation is successful.
     * This test verifies that the deleteAlert() method successfully deletes an alert
     * and does not throw any exceptions. It also checks that the delete() method is
     * called once with the correct alert.
     */
    @Test
    public void testDeleteAlert_Success() {
        when(alertsRepository.findById(mockAlert.getId())).thenReturn(Optional.of(mockAlert));
        when(userRepository.findById(mockUser.getId())).thenReturn(Optional.of(mockUser));

        assertDoesNotThrow(() -> alertsService.deleteAlert(mockAlert.getId(), mockUser));
        verify(alertsRepository, times(1)).delete(mockAlert);
    }

    /**
     * Tests the createAlert() method of the AlertsService class when the user is not found.
     * This test verifies that the createAlert() method throws an IllegalArgumentException
     * with the correct message when the user associated with the alert is not found.
     */
    @Test
    public void testCreateAlert_UserNotFound() {
        AlertCreatedService alertCreatedService = new AlertCreatedService();
        alertCreatedService.setUser(mockUser);
        alertCreatedService.setCryptoCurrency(mockCryptoCurrency);
        alertCreatedService.setPriceThreshold(50000.0);
        alertCreatedService.setVariationThreshold(5.0);
        alertCreatedService.setName("Bitcoin Alert");

        when(userRepository.findById(mockUser.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                alertsService.createAlert(alertCreatedService));

        assertEquals("L'utilisateur n'existe pas.", exception.getMessage());
    }

    /**
     * Tests the updateAlert() method of the AlertsService class when the user is not authorized.
     * This test verifies that the updateAlert() method throws an IllegalStateException
     * with the correct message when a user, who does not own the alert, tries to update it.
     */
    @Test
    public void testUpdateAlert_NotAuthorized() {
        User anotherUser = new User();
        anotherUser.setId(2L);

        AlertUpdateService alertUpdateService = new AlertUpdateService();
        alertUpdateService.setName("Updated Bitcoin Alert");
        alertUpdateService.setPriceThreshold(60000.0);
        alertUpdateService.setVariationThreshold(5.0);

        when(alertsRepository.findById(mockAlert.getId())).thenReturn(Optional.of(mockAlert));
        when(userRepository.findById(anotherUser.getId())).thenReturn(Optional.of(anotherUser));

        Exception exception = assertThrows(IllegalStateException.class, () ->
                alertsService.updateAlert(mockAlert.getId(), anotherUser, alertUpdateService));

        assertEquals("Vous n'êtes pas autorisé à modifier cette alerte.", exception.getMessage());
    }

    /**
     * Tests the deleteAlert() method of the AlertsService class when the user is not authorized.
     * This test verifies that the deleteAlert() method throws an IllegalStateException
     * with the correct message when a user, who does not own the alert, tries to delete it.
     */
    @Test
    public void testDeleteAlert_NotAuthorized() {
        User anotherUser = new User();
        anotherUser.setId(2L);

        when(alertsRepository.findById(mockAlert.getId())).thenReturn(Optional.of(mockAlert));
        when(userRepository.findById(anotherUser.getId())).thenReturn(Optional.of(anotherUser));

        Exception exception = assertThrows(IllegalStateException.class, () ->
                alertsService.deleteAlert(mockAlert.getId(), anotherUser));

        assertEquals("Vous n'êtes pas autorisé à supprimer cette alerte.", exception.getMessage());
    }
}
