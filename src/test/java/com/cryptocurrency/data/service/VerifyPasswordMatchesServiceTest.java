package com.cryptocurrency.data.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The VerifyPasswordMatchesServiceTest class is a JUnit test class for the VerifyPasswordMatchesService class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class VerifyPasswordMatchesServiceTest {

    /**
     * Test that the VerifyPasswordMatchesService can correctly determine if a password
     * matches the expected pattern. The expected pattern is at least 8 characters long,
     * and must contain at least one lowercase letter, one uppercase letter, one number,
     * and one special character.
     */
    @Test
    public void testVerifyPasswordMatches() {
        String password = "Password123@";
        String badPassword = "password";
        boolean result = VerifyPasswordMatchesService.isValidPassword(password);
        boolean badResult = VerifyPasswordMatchesService.isValidPassword(badPassword);
        assertFalse(badResult);
        assertTrue(result);
    }
}
