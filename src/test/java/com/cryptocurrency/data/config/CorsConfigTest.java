package com.cryptocurrency.data.config;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * The CorsConfigTest class is a JUnit test class for the CorsConfig class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class CorsConfigTest {

    /**
     * Verifies that the CorsConfig class's corsConfigurer method returns a
     * non-null WebMvcConfigurer and that the CorsConfig object is not null.
     */
    @Test
    public void testCorsConfigurer() {
        CorsConfig corsConfig = new CorsConfig();
        WebMvcConfigurer webMvcConfigurer = corsConfig.corsConfigurer();

        assertNotNull(webMvcConfigurer);
        assertNotNull(corsConfig);
    }
}
