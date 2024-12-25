package com.cryptocurrency.data.utils;

/**
 * A utility class for generating random tokens.
 * A token is a random 32-character hexadecimal string, which is suitable for use
 * as a token in a web application.
 *
 * @author Mouhamadou Ahibou DIALLO
 */
public class GenerateToken {

    /**
     * Generates a random token. The token is a random 32-character hexadecimal
     * string, which is suitable for use as a token in a web application.
     *
     * @return A random token.
     */
    public static String generateToken() {
        return java.util.UUID.randomUUID().toString();
    }
}
