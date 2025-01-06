package com.cryptocurrency.data.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.*;

/**
 * The ConfigurationApplicationSecurity class is a Spring configuration class for configuring application security.
 * It provides a security filter chain with the following configuration:
 * Author: Mouhamadou Ahibou DIALLO
 */
@Configuration
@EnableWebSecurity
public class ConfigurationApplicationSecurity {

    /**
     * This method creates a security filter chain with the following configuration:
     * <ul>
     *     <li>Disable CSRF protection</li>
     *     <li>Permit all POST requests to "/api/users/create", "/api/users/login", and "/api/users/verify-token" endpoints</li>
     *     <li>Require authentication for all other requests</li>
     *     <li>Disable HTTP basic authentication</li>
     * </ul>
     * @param httpSecurity the HttpSecurity object to be configured
     * @return a SecurityFilterChain with the specified configuration
     * @throws Exception if an error occurs while building the security filter chain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return
                httpSecurity
                        .csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(auth -> auth
                                .requestMatchers(POST, "/api/users/create").permitAll()
                                .requestMatchers(POST, "/api/users/login").permitAll()
                                .requestMatchers(POST, "/api/users/verify-token").permitAll()
                                .requestMatchers(POST, "api/users/logout").permitAll()
                                .requestMatchers(GET, "/api/users").permitAll()
                                .requestMatchers(PUT, "api/users/update").permitAll()
                                .requestMatchers(DELETE, "api/users/delete/{id}").permitAll()
                                .requestMatchers(PUT, "api/users/update-token/{id}").permitAll()
                                .requestMatchers(POST, "/api/users/forgot-password").permitAll()
                                .requestMatchers(POST, "/api/users/reset-password").permitAll()
                                .requestMatchers(GET, "/api/users/{id}").permitAll()
                                .requestMatchers(POST, "api/alerts/create").permitAll()
                                .requestMatchers(DELETE, "api/alerts/delete/{id}").permitAll()
                                .requestMatchers(PUT, "api/alerts/update/{id}").permitAll()
                                .requestMatchers(POST, "api/users/downgrade-to-standard").permitAll()
                                .requestMatchers(POST, "api/users/upgrade-to-premium").permitAll()
                                .requestMatchers(POST, "api/portfolio/create").permitAll()
                                .requestMatchers(DELETE, "api/portfolio/delete/{id}").permitAll()
                                .requestMatchers(PUT, "api/portfolio/update/{id}").permitAll()
                                .anyRequest().authenticated()
                        )
                        .httpBasic(Customizer.withDefaults())
                        .build();
    }

    /**
     * Creates a PasswordEncoder bean for encoding user passwords.
     * <p>
     * The BCryptPasswordEncoder is used by default to encode user passwords.
     * </p>
     * @return a PasswordEncoder bean for encoding user passwords
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Creates a UserDetailsService bean for retrieving user details.
     * <p>
     * In this case, an InMemoryUserDetailsManager is used to store user details in memory.
     * <p>
     * The user "momo" with the password "*******" is created with the role "USER".
     * </p>
     * @return a UserDetailsService bean for retrieving user details
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("momo")
                        .password(passwordEncoder().encode("Avignon2024@?"))
                        .roles("USER")
                        .build()
        );
    }
}
