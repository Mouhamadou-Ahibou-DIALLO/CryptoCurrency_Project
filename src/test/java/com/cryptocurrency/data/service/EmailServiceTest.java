package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.Alerts;
import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

/**
 * The EmailServiceTest class is a JUnit test class for the EmailService class.
 * Author: Mouhamadou Ahibou DIALLO
 */
class EmailServiceTest {

    /**
     * The emailService object to be tested.
     */
    private EmailService emailService;

    /**
     * The mock JavaMailSender object.
     */
    private JavaMailSender mailSender;

    /**
     * Initializes the test environment before each test.
     * <p>
     * Creates a mock JavaMailSender object and an EmailService object
     * with the mock JavaMailSender object.
     */
    @BeforeEach
    public void setUp() {
        mailSender = Mockito.mock(JavaMailSender.class);
        emailService = new EmailService(mailSender);
    }

    /**
     * Test the sendNotification() method of the EmailService class.
     * <p>
     * Verifies that the sendNotification() method sends an email with the correct details.
     */
    @Test
    public void sendNotification_ShouldSendEmailWithCorrectDetails() {
       User user = new User();
        user.setEmail("test@example.com");
        user.setUsername("JohnDoe");

        CryptoCurrency currency = new CryptoCurrency(1L,"Bitcoin", "BTC", 1);

        Alerts alert = new Alerts();
        alert.setUser(user);
        alert.setCryptoCurrency(currency);
        alert.setPriceThreshold(30000.0);

        double currentPrice = 35000.0;
        emailService.sendNotification(alert, currentPrice);

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender, times(1)).send(captor.capture());

        SimpleMailMessage sentMessage = captor.getValue();
        assertEquals("test@example.com", Objects.requireNonNull(sentMessage.getTo())[0]);

        assertEquals("Alerte CryptoCurrency", sentMessage.getSubject());
        assertEquals("Bonjour JohnDoe,\n\n"
                        + "Le prix de la cryptomonnaie Bitcoin a atteint ou dépassé le seuil que vous avez défini !\n\n"
                        + "Prix actuel : 35000.0 $\n"
                        + "Seuil défini : 30000.0 $\n\n"
                        + "Cordialement,\nVotre application La Cryptomonnaie de l'avenir",
                sentMessage.getText());
    }

    /**
     * Test the sendEmail() method of the EmailService class.
     * <p>
     * Verifies that the sendEmail() method sends an email with the correct details.
     */
    @Test
    public void testSendEmail() {
        emailService.sendEmail("test@example.com", "Test Subject", "Test Message");
        verify(mailSender, times(1)).send(Mockito.any(SimpleMailMessage.class));
    }
}
