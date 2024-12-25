package com.cryptocurrency.data.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The EncodedToken class is a utility class for encoding tokens for use in authentication.
 * A token is encoded by encoding its value using the BCryptPasswordEncoder.
 *
 * @author Mouhamadou Ahibou DIALLO
 */
public class EncodedToken {

    /**
     * The password encoder used to encode tokens.
     */
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Encodes a token for use in authentication.
     *
     * @param token the token to encode
     * @return the encoded token
     */
    public static String encode(String token) {
        return passwordEncoder.encode(token);
    }
}
