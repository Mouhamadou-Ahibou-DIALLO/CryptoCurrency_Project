package com.cryptocurrency.data.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The EncodedPassword class is a utility class for encoding passwords using the BCryptPasswordEncoder.
 * It provides a method to encode a password using the encoder.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class EncodedPassword {

    /**
     * The BCryptPasswordEncoder used for encoding passwords.
     */
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Default constructor for the EncodedPassword class.
     */
    public EncodedPassword() {}

    /**
     * Encodes a password using the BCryptPasswordEncoder.
     *
     * @param password the password to encode
     * @return the encoded password
     */
    public static String encode(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * Checks if the given password matches the given password hash.
     *
     * @param password the password to check
     * @param passwordHash the password hash to check against
     * @return true if the password matches the password hash, false otherwise
     */
    public static boolean isRightPassword(String password, String passwordHash) {
        return passwordEncoder.matches(password, passwordHash);
    }
}
