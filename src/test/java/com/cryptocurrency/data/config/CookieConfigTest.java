package com.cryptocurrency.data.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The CookieConfigTest class is a JUnit test class for the CookieConfig class.
 * Author: Mouhamadou Ahibou DIALLO
 */
@SpringBootTest
public class CookieConfigTest {

    /**
     * The TestConfig class is a Spring configuration class for testing the CookieConfig class.
     * Author: Mouhamadou Ahibou DIALLO
     */
    @Configuration
    @Import(CookieConfig.class)
    static class TestConfig {
    }

    /**
     * The cookieSerializer field is an instance of the CookieSerializer interface,
     * which is used to serialize and deserialize session cookies.
     */
    @Autowired
    private CookieSerializer cookieSerializer;

    /**
     * Verifies that the cookie serializer is correctly configured.
     * <p>
     *     Asserts that the cookie serializer is not null and is an instance of
     *     DefaultCookieSerializer. Additionally, it asserts that the default
     *     cookie serializer is not null.
     * </p>
     */
    @Test
    public void testCookieSerializerConfiguration() {
        assertNotNull(cookieSerializer);
        assertInstanceOf(DefaultCookieSerializer.class, cookieSerializer);

        DefaultCookieSerializer defaultCookieSerializer = (DefaultCookieSerializer) cookieSerializer;
        assertNotNull(defaultCookieSerializer);
    }

    /**
     * Tests the configuration of the CookieConfig class.
     * <p>
     *     Verifies that the CookieConfig class is not null and that the cookie serializer
     *     bean returned by the CookieConfig class is not null.
     * </p>
     */
    @Test
    public void testCookieConfig() {
        CookieConfig cookieConfig = new CookieConfig();
        assertNotNull(cookieConfig.cookieSerializer());
    }
}