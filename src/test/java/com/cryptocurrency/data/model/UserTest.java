package com.cryptocurrency.data.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The UserTest class is a JUnit test class for the User class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class UserTest {

    /**
     * The user object to be tested.
     */
    private User user;

    /**
     * The setUp method is used to initialize the user object before each test.
     */
    @BeforeEach
    public void setUp() {
        user = new User(1L, "user1",  "email1", "tokenHash1", "passwordHash1");
    }

    /**
     * The testGetters method tests the getters of the User class.
     * Ensures that the getters return the expected values.
     */
    @Test
    public void testGetters() {
        assertEquals(1L, user.getId(), "The id of the user should be 1L");
        assertEquals("user1", user.getUsername(), "The username of the user should be 'user1'");
        assertEquals("email1", user.getEmail(), "The email of the user should be 'email1'");
        assertEquals("tokenHash1", user.getTokenHash(), "The token hash of the user should be 'tokenHash1'");
        assertEquals("passwordHash1", user.getPasswordHash(), "The password hash of the user should be 'passwordHash1'");
    }

    /**
     * The testSetters method tests the setters of the User class.
     * Ensures that the setters correctly update the values of the object's properties.
     */
    @Test
    public void testSetters() {
        user.setId(2L);
        user.setUsername("user2");
        user.setEmail("email2");
        user.setTokenHash("tokenHash2");
        user.setPasswordHash("passwordHash2");

        assertEquals(2L, user.getId(), "The id of the user should be 2L");
        assertEquals("user2", user.getUsername(), "The username of the user should be 'user2'");
        assertEquals("email2", user.getEmail(), "The email of the user should be 'email2'");
        assertEquals("tokenHash2", user.getTokenHash(), "The token hash of the user should be 'tokenHash2'");
        assertEquals("passwordHash2", user.getPasswordHash(), "The password hash of the user should be 'passwordHash2'");
    }

    /**
     * The testConstructor method tests the constructor of the User class.
     * Ensures that the constructor correctly initializes the object's properties.
     */
    @Test
    public void testConstructor() {
        assertEquals(1L, user.getId(), "The id of the user should be 1L");
        assertEquals("user1", user.getUsername(), "The username of the user should be 'user1'");
        assertEquals("email1", user.getEmail(), "The email of the user should be 'email1'");
        assertEquals("tokenHash1", user.getTokenHash(), "The token hash of the user should be 'tokenHash1'");
        assertEquals("passwordHash1", user.getPasswordHash(), "The password hash of the user should be 'passwordHash1'");

        User user1 = new User();
        user1.setStatut("normal");

        assertEquals("normal", user1.getStatut(), "The statut of the user should be 'normal'");
    }

    /**
     * Tests the toString method of the User class.
     * Ensures that the string representation of the User object
     * matches the expected format.
     */
    @Test
    public void testToString() {
        String expected = "User{" +
                "id=" + user.getId() +
                ", username='" + user.getUsername() + '\'' +
                ", email='" + user.getEmail() + '\'' +
                ", tokenHash='" + user.getTokenHash() + '\'' +
                ", passwordHash='" + user.getPasswordHash() + '\'' +
                '}';
        assertEquals(expected, user.toString());
    }
}
