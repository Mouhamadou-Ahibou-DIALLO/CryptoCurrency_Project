package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.Alerts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * The EmailService class is a Spring service for sending email notifications.
 * Author: Mouhamadou Ahibou DIALLO
 */
@Service
public class EmailService {

    /**
     * The JavaMailSender object used to send emails.
     */
    @Autowired
    private JavaMailSender mailSender;

    /**
     * Constructor for the EmailService class.
     * @param mailSender The JavaMailSender object used to send emails.
     */
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Default constructor for the EmailService class.
     */
    public EmailService() {}

    /**
     * Send an email notification to the user associated with the alert.
     * @param alert The alert object containing the user and price threshold.
     * @param currentPrice The current price or variation of the cryptocurrency.
     */
    public void sendNotification(Alerts alert, double currentPrice) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(alert.getUser().getEmail());
        message.setSubject("Alerte CryptoCurrency");

        message.setText("Bonjour " + alert.getUser().getUsername() + ",\n\n"
                + "Le prix de la cryptomonnaie " + alert.getCryptoCurrency().getName()
                + " a atteint ou dépassé le seuil que vous avez défini !\n\n"
                + "Prix actuel : " + currentPrice + " $\n"
                + "Seuil défini : " + alert.getPriceThreshold() + " $\n\n"
                + "Cordialement,\nVotre application La Cryptomonnaie de l'avenir");

        mailSender.send(message);
        System.out.println("le message a ete envoyé: " + message);
        System.out.println("Notification envoyée à " + alert.getUser().getEmail());
    }

    /**
     * Sends an email with the specified details.
     *
     * @param email The recipient's email address.
     * @param subject The subject of the email.
     * @param text The body text of the email.
     */
    public void sendEmail(String email, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
