package com.cryptocurrency.data.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * The CryptoPriceHistoryTest class is a JUnit test class for the CryptoPriceHistory class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class CryptoPriceHistoryTest {

    /**
     * The cryptoPriceHistory object to be tested.
     */
    private CryptoPriceHistory cryptoPriceHistory;

    /**
     * Initializes the cryptoPriceHistory object with the current timestamp and a price of 100.0
     * before each test is executed.
     */
    @BeforeEach
    public void setUp() {
        LocalDateTime timestamp = LocalDateTime.now();
        Double price = 100.0;
        cryptoPriceHistory = new CryptoPriceHistory(timestamp, price);
    }

    /**
     * Tests the getters and setters of the CryptoPriceHistory class.
     * This test case sets the timestamp and price of the CryptoPriceHistory object
     * and then verifies that the getters return the expected values.
     */
    @Test
    public void testGetteursAndSetteurs() {
        Double price = 100.0;
        String date = "2023-01-01T00:00:00";
        cryptoPriceHistory.setTimestamp(LocalDateTime.parse(date));
        cryptoPriceHistory.setPrice(price);

        assertEquals(2023, cryptoPriceHistory.getTimestamp().getYear(), "The year should be 2023");
        assert cryptoPriceHistory.getPrice().equals(price);
    }

    /**
     * Tests the toString method of the CryptoPriceHistory class.
     * This test case compares the output of the toString method with a string
     * containing the expected values of the timestamp and price.
     */
    @Test
    public void testToString() {
        String expected = "CryptoPriceHistory{" +
                "timestamp=" + cryptoPriceHistory.getTimestamp() +
                ", price=" + cryptoPriceHistory.getPrice() +
                '}';
        assert cryptoPriceHistory.toString().equals(expected);
    }
}
