package com.cryptocurrency.data.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * The EncodedTokenTest class is a JUnit test class for the EncodedToken class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class EncodedTokenTest {

    /**
     * Verifies that the token is not the same as the encoded token.
     * This test method encodes a token and verifies that the result is not the
     * same as the original token.
     */
    @Test
    public void testEncode() {
        String token = "testToken";
        String encodedToken = EncodedToken.encode(token);
        assertNotEquals(token, encodedToken);
    }
}
