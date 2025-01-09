package com.cryptocurrency.data.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The EncodedPasswordTest class is a JUnit test class for the EncodedPassword class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class EncodedPasswordTest {

    /**
     * Verifies that the password encoded by the EncodedPassword class is not
     * equal to the original password.
     */
    @Test
    public void testEncode() {
        String password = "testPassword";
        String encodedPassword = EncodedPassword.encode(password);
        assertNotEquals(password, encodedPassword);
    }

    /**
     * Verifies that the password encoded by the EncodedPassword class can be
     * matched using the isRightPassword method.
     */
    @Test
    public void testIsRightPassword() {
        String password = "testPassword";
        String encodedPassword = EncodedPassword.encode(password);
        boolean isRightPassword = EncodedPassword.isRightPassword(password, encodedPassword);
        assertTrue(isRightPassword);
    }
}
