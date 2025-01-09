package com.cryptocurrency.data.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The GenerateTokenTest class is a JUnit test class for the GenerateToken class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class GenerateTokenTest {

    /**
     * Tests the generateToken method of the GenerateToken class.
     * Ensures that a token is generated and that it matches the UUID format.
     */
    @Test
    public void testGenerateToken() {
        String token = GenerateToken.generateToken();
        assertNotNull(token, "Generated token should not be null");
        assertEquals(36, token.length(), "Generated token should have 36 characters");
        assertTrue(token.matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"),
                "Generated token should match the UUID format");
    }
}
