package com.cryptocurrency.data.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The JacksonConfig class is a Spring configuration class for configuring Jackson.
 * Author: Mouhamadou Ahibou DIALLO
 */
@Configuration
public class JacksonConfig {

    /**
     * Default constructor for the JacksonConfig class.
     */
    public JacksonConfig() {}

        /**
         * This method creates a new instance of an ObjectMapper and configures it
         * to serialize and deserialize the dates in ISO format, and to not
         * fail when a bean has no properties.
         *
         * @return ObjectMapper
         */
        @Bean
        public ObjectMapper objectMapper() {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            return mapper;
        }
}
