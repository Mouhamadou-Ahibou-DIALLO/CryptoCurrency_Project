package com.cryptocurrency.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CryptoCurrencyProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptoCurrencyProjectApplication.class, args);
        System.out.println("Step done");
    }
}
