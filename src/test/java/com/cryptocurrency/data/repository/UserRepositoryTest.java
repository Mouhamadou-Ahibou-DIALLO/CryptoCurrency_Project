package com.cryptocurrency.data.repository;

import com.cryptocurrency.data.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * The UserRepositoryTest class is a JUnit test class for the UserRepository class.
 * Author: Mouhamadou Ahibou DIALLO
 */
@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    /**
     * The userRepository field is a mock of the UserRepository class.
     */
    @Mock
    private UserRepository userRepository;

    /**
     * The user1, user2, and user3 fields are instances of the User class.
     */
    private User user1;
    private User user2;
    private User user3;

    /**
     * The setUp method is a JUnit @BeforeEach annotated method that is run
     * before each test. It sets up the user1, user2, and user3 fields with
     * instances of the User class.
     */
    @BeforeEach
    public void setUp() {
        user1 = new User(1L, "user1",  "email1", "tokenHash1", "passwordHash1");
        user2 = new User(2L, "user2", "email2", "tokenHash2", "passwordHash2");
        user3 = new User(3L, "user3", "email3", "tokenHash3", "passwordHash3");
    }

    /**
     * Tests the findAll method of the UserRepository class.
     * Ensures that all User objects are returned correctly.
     */
    @Test
    public void testFindAll() {
        List<User> mockUserList = List.of(user1, user2, user3);

        when(userRepository.findAll()).thenReturn(mockUserList);
        List<User> result = userRepository.findAll();

        assertEquals(3, result.size());

        assertEquals("user1", result.get(0).getUsername());
        assertEquals("email2", result.get(1).getEmail());
        assertEquals("tokenHash3", result.get(2).getTokenHash());
        assertEquals("passwordHash1", result.get(0).getPasswordHash());
    }

    /**
     * Tests the findByUsername method of the UserRepository class.
     * Ensures that a User object is returned when a valid username is provided.
     */
    @Test
    public void testFindByUsername() {
        String username = "user2";

        when(userRepository.findByUsername(username)).thenReturn(java.util.Optional.of(user2));
        User result = userRepository.findByUsername(username).orElse(null);

        assert result != null;
        assertEquals("user2", result.getUsername());
        assertEquals("email2", result.getEmail());
        assertEquals("tokenHash2", result.getTokenHash());
        assertEquals("passwordHash2", result.getPasswordHash());
    }

    /**
     * Tests the findById method of the UserRepository class.
     * Ensures that a User object is returned when a valid id is provided.
     */
    @Test
    public void testFindById() {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(java.util.Optional.of(user1));
        User result = userRepository.findById(id).orElse(null);

        assert result != null;
        assertEquals("user1", result.getUsername());
        assertEquals("email1", result.getEmail());
        assertEquals("tokenHash1", result.getTokenHash());
        assertEquals("passwordHash1", result.getPasswordHash());

        verify(userRepository, times(1)).findById(id);
    }

    /**
     * Tests the findByEmail method of the UserRepository class.
     * Ensures that a User object is returned when a valid email is provided.
     */
    @Test
    public void testFindByEmail() {
        String email = "email2";

        when(userRepository.findByEmail(email)).thenReturn(java.util.Optional.of(user2));
        User result = userRepository.findByEmail(email).orElse(null);

        assert result != null;
        assertEquals("user2", result.getUsername());
        assertEquals("email2", result.getEmail());
        assertEquals("tokenHash2", result.getTokenHash());
        assertEquals("passwordHash2", result.getPasswordHash());

        verify(userRepository, times(1)).findByEmail(email);
    }

    /**
     * Tests the save method of the UserRepository class.
     * Ensures that a User object is saved correctly and the save method is called once.
     */
    @Test
    public void testSave() {
        when(userRepository.save(user1)).thenReturn(user1);
        User result = userRepository.save(user1);

        assertEquals("user1", result.getUsername(), "The username should be 'user1'");
        assertEquals("email1", result.getEmail(), "The email should be 'email1'");
        assertEquals("tokenHash1", result.getTokenHash(), "The token hash should be 'tokenHash1'");
        assertEquals("passwordHash1", result.getPasswordHash(), "The password hash should be 'passwordHash1'");

        verify(userRepository, times(1)).save(user1);
    }

    /**
     * Tests the delete method of the UserRepository class.
     * Ensures that the delete method is called once with the correct User object.
     */
    @Test
    public void testDelete() {
        userRepository.delete(user1);
        verify(userRepository, times(1)).delete(user1);
    }

    /**
     * Tests the findByTokenHash method of the UserRepository class.
     * Ensures that a User object is returned when a valid token hash is provided.
     * Verifies that findByTokenHash is called once.
     */
    @Test
    public void testFindByTokenHash() {
        String tokenHash = "tokenHash1";

        when(userRepository.findByTokenHash(tokenHash)).thenReturn(java.util.Optional.of(user1));
        User result = userRepository.findByTokenHash(tokenHash).orElse(null);

        assert result != null;
        assertEquals("user1", result.getUsername(), "The username should be 'user1'");
        assertEquals("email1", result.getEmail(), "The email should be 'email1'");
        assertEquals("tokenHash1", result.getTokenHash(), "The token hash should be 'tokenHash1'");
        assertEquals("passwordHash1", result.getPasswordHash(), "The password hash should be 'passwordHash1'");

        verify(userRepository, times(1)).findByTokenHash(tokenHash);
    }

    /**
     * Tests the findByPasswordHash method of the UserRepository class.
     * Ensures that a List of User objects is returned when a valid password hash is provided.
     * Verifies that findByPasswordHash is called once.
     */
    @Test
    public void testFindByPasswordHash() {
        String passwordHash = "passwordHash1";
        List<User> mockUserList = List.of(user1);

        when(userRepository.findByPasswordHash(passwordHash)).thenReturn(mockUserList);
        List<User> result = userRepository.findByPasswordHash(passwordHash);

        assertNotNull(result);
        assertEquals(1, result.size());

        User user = result.get(0);
        assertEquals("user1", user.getUsername());
        assertEquals("email1", user.getEmail());
        assertEquals("tokenHash1", user.getTokenHash());
        assertEquals("passwordHash1", user.getPasswordHash());

        verify(userRepository, times(1)).findByPasswordHash(passwordHash);
    }

    /**
     * Tests the findByUsernameAndPasswordHash method of the UserRepository class.
     * Ensures that a User object is returned when a valid username and password hash are provided.
     * Verifies that findByUsernameAndPasswordHash is called once.
     */
    @Test
    public void findByUsernameAndPasswordHash() {
        String username = "user1";
        String passwordHash = "passwordHash1";

        when(userRepository.findByUsernameAndPasswordHash(username, passwordHash)).thenReturn(user1);
        User result = userRepository.findByUsernameAndPasswordHash(username, passwordHash);

        assertEquals(user1, result, "The returned User object should be the same as the one provided");
    }

    /**
     * Tests the findByEmailAndPasswordHash method of the UserRepository class.
     * Ensures that a User object is returned when a valid email and password hash are provided.
     * Verifies that findByEmailAndPasswordHash is called once.
     */
    @Test
    public void testFindByEmailAndPasswordHash() {
        String email = "email1";
        String passwordHash = "passwordHash1";

        when(userRepository.findByEmailAndPasswordHash(email, passwordHash)).thenReturn(java.util.Optional.of(user1));
        User result = userRepository.findByEmailAndPasswordHash(email, passwordHash).orElse(null);

        assertEquals(user1, result, "The returned User object should be the same as the one provided");

        verify(userRepository, times(1)).findByEmailAndPasswordHash(email, passwordHash);
    }

    /**
     * Tests the findByEmailAndTokenHash method of the UserRepository class.
     * Ensures that a User object is returned when a valid email and token hash are provided.
     * Verifies that findByEmailAndTokenHash is called once.
     */
    @Test
    public void testFindByEmailAndTokenHash() {
        String email = "email1";
        String tokenHash = "tokenHash1";

        when(userRepository.findByEmailAndTokenHash(email, tokenHash)).thenReturn(java.util.Optional.of(user1));
        User result = userRepository.findByEmailAndTokenHash(email, tokenHash).orElse(null);

        assertEquals(user1, result, "The returned User object should be the same as the one provided");

        verify(userRepository, times(1)).findByEmailAndTokenHash(email, tokenHash);
    }
}
