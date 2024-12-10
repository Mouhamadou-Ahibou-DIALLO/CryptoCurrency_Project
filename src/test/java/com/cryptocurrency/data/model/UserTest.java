package com.cryptocurrency.data.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User(1L, "user1",  "email1", "tokenHash1", "passwordHash1");
    }

    @Test
    public void testGetters() {
        assertEquals(1L, user.getId());
        assertEquals("user1", user.getUsername());
        assertEquals("email1", user.getEmail());
        assertEquals("tokenHash1", user.getTokenHash());
        assertEquals("passwordHash1", user.getPasswordHash());
    }

    @Test
    public void testSetters() {
        user.setId(2L);
        user.setUsername("user2");
        user.setEmail("email2");
        user.setTokenHash("tokenHash2");
        user.setPasswordHash("passwordHash2");

        assertEquals(2L, user.getId());
        assertEquals("user2", user.getUsername());
        assertEquals("email2", user.getEmail());
        assertEquals("tokenHash2", user.getTokenHash());
        assertEquals("passwordHash2", user.getPasswordHash());
    }

    @Test
    public void testConstructor() {
        assertEquals(1L, user.getId());
        assertEquals("user1", user.getUsername());
        assertEquals("email1", user.getEmail());
        assertEquals("tokenHash1", user.getTokenHash());
        assertEquals("passwordHash1", user.getPasswordHash());
    }
}
