package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.Alerts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendNotification(Alerts alert, double currentPrice) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(alert.getUser().getEmail());
        message.setSubject("Alerte CryptoCurrency");

        message.setText("Bonjour " + alert.getUser().getUsername() + ",\n\n"
                + "Le prix de la cryptomonnaie " + alert.getMarketData().getCryptoCurrency().getName()
                + " a atteint ou dépassé le seuil que vous avez défini !\n\n"
                + "Prix actuel : " + currentPrice + " $\n"
                + "Seuil défini : " + alert.getPriceThreshold() + " $\n\n"
                + "Cordialement,\nVotre application Crypto");

        mailSender.send(message);
        System.out.println("Notification envoyée à " + alert.getUser().getEmail());
    }
}
