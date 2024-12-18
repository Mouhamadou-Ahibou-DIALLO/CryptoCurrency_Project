package com.cryptocurrency.data.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The LoginRequestServiceTest class is a JUnit test class for the LoginRequestService class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class LoginRequestServiceTest {

    /**
     * The loginRequestService object is used to test the LoginRequestService class.
     */
    private LoginRequestService loginRequestService;

    /**
     * Sets up the test environment before each test.
     * Creates a LoginRequestService object with default values.
     */
    @BeforeEach
    public void setUp() {
        loginRequestService = new LoginRequestService("email", "password");
    }

    /**
     * Tests the getEmail method of the LoginRequestService class.
     * Ensures that the email of the user is returned correctly.
     */
    @Test
    public void testGetEmail() {
        assertEquals("email", loginRequestService.getEmail());
    }

    /**
     * Tests the getPassword method of the LoginRequestService class.
     * Ensures that the password of the user is returned correctly.
     */
    @Test
    public void testGetPassword() {
        assertEquals("password", loginRequestService.getPassword());
    }

    /**
     * Tests the setEmail method of the LoginRequestService class.
     * Ensures that the email of the user is set correctly.
     */
    @Test
    public void testSetEmail() {
        loginRequestService.setEmail("newEmail");
        assertEquals("newEmail", loginRequestService.getEmail());
    }

    /**
     * Tests the setPassword method of the LoginRequestService class.
     * Ensures that the password of the user is set correctly.
     */
    @Test
    public void testSetPassword() {
        loginRequestService.setPassword("newPassword");
        assertEquals("newPassword", loginRequestService.getPassword());
    }

    /**
     * Tests the constructor of the LoginRequestService class.
     * Ensures that the email and password of the user are set correctly.
     */
    @Test
    public void testConstructor() {
        assertEquals("email", loginRequestService.getEmail(), "The email of the user should be 'email'");
        assertEquals("password", loginRequestService.getPassword(), "The password of the user should be 'password'");
    }
}
