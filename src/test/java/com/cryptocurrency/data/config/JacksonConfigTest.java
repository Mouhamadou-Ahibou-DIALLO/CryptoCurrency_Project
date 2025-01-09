package com.cryptocurrency.data.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The JacksonConfigTest class is a JUnit test class for the JacksonConfig class.
 * Author: Mouhamadou Ahibou DIALLO
 */
@SpringBootTest
public class JacksonConfigTest {

    /**
     * The objectMapper field is an instance of the ObjectMapper class that is
     * created by the JacksonConfig class.
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Tests the configuration of the ObjectMapper bean provided by the JacksonConfig class.
     * Verifies that the ObjectMapper is not null, that it does not write dates as timestamps,
     * that it does not have the JavaTimeModule registered, and that it does not fail on empty beans.
     */
    @Test
    public void testObjectMapperConfiguration() {
        assertNotNull(objectMapper);
        assertFalse(objectMapper.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS));
        assertFalse(objectMapper.getRegisteredModuleIds().contains(JavaTimeModule.class.getName()));
        assertFalse(objectMapper.isEnabled(SerializationFeature.FAIL_ON_EMPTY_BEANS));
    }

    /**
     * Tests the configuration of the ObjectMapper bean provided by the JacksonConfig class.
     * This test is equivalent to {@link #testObjectMapperConfiguration()} but it creates a new instance
     * of the JacksonConfig class instead of using the one provided by Spring.
     */
    @Test
    public void testObjetMapperConfiguration2() {
        JacksonConfig jacksonConfig = new JacksonConfig();
        assertNotNull(jacksonConfig.objectMapper());
        assertFalse(jacksonConfig.objectMapper().isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS));
        assertFalse(jacksonConfig.objectMapper().getRegisteredModuleIds().contains(JavaTimeModule.class.getName()));
        assertFalse(jacksonConfig.objectMapper().isEnabled(SerializationFeature.FAIL_ON_EMPTY_BEANS));
    }
}
