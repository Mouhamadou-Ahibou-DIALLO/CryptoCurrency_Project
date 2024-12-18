package com.cryptocurrency.data.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The TokenRequestServiceTest class is a JUnit test class for the TokenRequestService class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class TokenRequestServiceTest {

    /**
     * The tokenRequestService object is used to test the TokenRequestService class.
     */
    private TokenRequestService tokenRequestService;

    /**
     * Sets up the test environment before each test.
     * Initializes the tokenRequestService with default values.
     */
    @BeforeEach
    public void setUp() {
        tokenRequestService = new TokenRequestService("email", "token");
    }

    /**
     * Tests the getEmail method of the TokenRequestService class.
     * Ensures that the email of the user is returned correctly.
     */
    @Test
    public void testGetEmail() {
        assertEquals("email", tokenRequestService.getEmail());
    }

    /**
     * Tests the getToken method of the TokenRequestService class.
     * Ensures that the token is returned correctly.
     */
    @Test
    public void testGetToken() {
        assertEquals("token", tokenRequestService.getToken());
    }

    /**
     * Tests the setEmail method of the TokenRequestService class.
     * Ensures that the email of the user is set correctly.
     */
    @Test
    public void testSetEmail() {
        String newEmail = "newEmail";
        tokenRequestService.setEmail(newEmail);
        assertEquals(newEmail, tokenRequestService.getEmail());
    }

    /**
     * Tests the setToken method of the TokenRequestService class.
     * Ensures that the token is set correctly.
     */
    @Test
    public void testSetToken() {
        String newToken = "newToken";
        tokenRequestService.setToken(newToken);
        assertEquals(newToken, tokenRequestService.getToken());
    }

    /**
     * Tests the constructor of the TokenRequestService class.
     * Ensures that the constructor correctly initializes the object's properties.
     */
    @Test
    public void testConstructor() {
        assertEquals("email", tokenRequestService.getEmail(), "The email of the user should be 'email'");
        assertEquals("token", tokenRequestService.getToken(), "The token should be 'token'");
    }
}
