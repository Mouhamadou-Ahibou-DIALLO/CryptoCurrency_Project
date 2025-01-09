package com.cryptocurrency.data.security;

import com.cryptocurrency.data.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * The ConfigurationApplicationSecurityTest class is a JUnit test class for the ConfigurationApplicationSecurity class.
 * Author: Mouhamadou Ahibou DIALLO
 */
@ExtendWith(MockitoExtension.class)
public class ConfigurationApplicationSecurityTest {

    /**
     * The mockMvc field is a MockMvc object used to perform HTTP requests and verify the responses.
     */
    private MockMvc mockMvc;

    /**
     * Sets up the MockMvc instance for testing by initializing it with a standalone setup
     * using the UserService class. This method is executed before each test to ensure
     * that the MockMvc instance is properly configured.
     */
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserService()).build();
    }

    /**
     * The securityConfig field is an instance of the ConfigurationApplicationSecurity class,
     * which is used to configure the security settings for the application.
     */
    @InjectMocks
    private ConfigurationApplicationSecurity securityConfig;

    /**
     * Tests that public endpoints in the security filter chain are accessible without authentication.
     * This test performs HTTP requests to various public endpoints and expects a 404 Not Found status.
     * The endpoints tested include:
     * - POST /api/users/create
     * - POST /api/users/login
     * - GET /api/users
     * - POST /api/portfolio/create
     * These endpoints should be accessible without authentication, indicating that they are configured
     * as public in the security filter chain.
     *
     * @throws Exception if an error occurs during the request execution
     */
    @Test
    public void testSecurityFilterChain_PublicEndpoints_AreAccessible() throws Exception {
        mockMvc.perform(post("/api/users/create"))
                .andExpect(status().isNotFound());

        mockMvc.perform(post("/api/users/login"))
                .andExpect(status().isNotFound());

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isNotFound());

        mockMvc.perform(post("/api/portfolio/create"))
                .andExpect(status().isNotFound());
    }

    /**
     * Tests that the PasswordEncoder bean correctly validates passwords for a user.
     * <p>
     * This test retrieves a UserDetailsService bean and a PasswordEncoder bean from
     * the security configuration. It then loads the user details for the user "momo"
     * and verifies that the password "Avignon2024@?" matches the encoded password
     * stored for the user.
     * </p>
     */
    @Test
    public void testPasswordEncoder_ValidatesPasswords() {
        UserDetailsService userDetailsService = securityConfig.userDetailsService();
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();

        UserDetails user = userDetailsService.loadUserByUsername("momo");
        assertTrue(passwordEncoder.matches("Avignon2024@?", user.getPassword()));
    }
}
