package com.cryptocurrency.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The CryptoCurrencyProjectApplication class is the main entry point for the Spring Boot application.
 * Author: Mouhamadou Ahibou DIALLO
 */
@SpringBootApplication
@EnableScheduling
public class CryptoCurrencyProjectApplication {

    /**
     * The main method serves as the entry point for the Spring Boot application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(CryptoCurrencyProjectApplication.class, args);
        System.out.println("Step done");
    }
}
