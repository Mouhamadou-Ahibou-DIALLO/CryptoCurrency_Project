package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.User;
import com.cryptocurrency.data.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * The UserServiceTest class is a JUnit test class for the UserService class.
 * Author: Mouhamadou Ahibou DIALLO
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    /**
     * The mock of user repository.
     */
    @Mock
    private UserRepository userRepository;

    /**
     * The user service.
     */
    @InjectMocks
    private UserService userService;

    /**
     * The users.
     */
    private User user1;
    private User user2;

    /**
     * The setUp method is used to initialize the user objects before each test.
     */
    @BeforeEach
    public void setUp() {
        user1 = new User(1L, "user1",  "email1", "tokenHash1", "passwordHash1");
        user2 = new User(2L, "user2", "email2", "tokenHash2", "passwordHash2");
    }


    /**
     * Tests the findAll method of the UserService class.
     * Ensures that all User objects are returned correctly.
     */

    @Test
    public void testFindAll() {
        List<User> mockUserList = List.of(user1, user2);

        when(userRepository.findAll()).thenReturn(mockUserList);
        List<User> result = userService.findAll();

        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals("email2", result.get(1).getEmail());
        assertEquals("tokenHash1", result.get(0).getTokenHash());
        assertEquals("passwordHash2", result.get(1).getPasswordHash());
    }

    /**
     * Tests the findByEmail method of the UserService class.
     * Ensures that a User object is returned when a valid email is provided.
     */
    @Test
    public void testFindByEmail() {
        String email = "email1";

        when(userRepository.findByEmail(email)).thenReturn(java.util.Optional.of(user1));
        User result = userService.findByEmail(email);

        assertEquals("user1", result.getUsername());
        assertEquals("email1", result.getEmail());
        assertEquals("tokenHash1", result.getTokenHash());
        assertEquals("passwordHash1", result.getPasswordHash());
    }

    /**
     * Tests the findById method of the UserService class.
     * Ensures that a User object is returned when a valid id is provided.
     */
    @Test
    public void testFindById() {
        Long id = 1L;
        User mockUser = user1;

        when(userRepository.findById(id)).thenReturn(java.util.Optional.of(mockUser));
        User result = userService.findById(id);

        assertEquals("user1", result.getUsername(), "The username should be 'user1'");
        assertEquals("email1", result.getEmail(), "The email should be 'email1'");
        assertEquals("tokenHash1", result.getTokenHash(), "The token hash should be 'tokenHash1'");
        assertEquals("passwordHash1", result.getPasswordHash(), "The password hash should be 'passwordHash1'");
    }

    /**
     * Tests the save method of the UserService class.
     * Ensures that a User object is saved correctly and the returned User object has the expected values.
     */
    @Test
    public void testSave() {
        User mockUser = user1;

        when(userRepository.save(mockUser)).thenReturn(mockUser);
        User result = userService.save(mockUser);

        assertEquals("user1", result.getUsername());
        assertEquals("email1", result.getEmail());
        assertEquals("tokenHash1", result.getTokenHash());
        assertEquals("passwordHash1", result.getPasswordHash());
    }

    /**
     * Tests the deleteById method of the UserService class.
     * Ensures that the deleteById method is called once with the correct id.
     */
    @Test
    public void testDeleteById() {
        Long id = 1L;
        userService.deleteById(id);
        verify(userRepository, times(1)).deleteById(id);
    }

    /**
     * Tests the findByUsername method of the UserService class.
     * Ensures that a User object is returned when a valid username is provided.
     */
    @Test
    public void testFindByUsername() {
        String username = "user1";

        when(userRepository.findByUsername(username)).thenReturn(java.util.Optional.of(user1));
        User result = userService.findByUsername(username);

        assertEquals("user1", result.getUsername());
        assertEquals("email1", result.getEmail());
        assertEquals("tokenHash1", result.getTokenHash());
        assertEquals("passwordHash1", result.getPasswordHash());
    }

    /**
     * Tests the findByTokenHash method of the UserService class.
     * Ensures that a User object is returned when a valid token hash is provided.
     */
    @Test
    public void testFindByTokenHash() {
        String tokenHash = "tokenHash1";

        when(userRepository.findByTokenHash(tokenHash)).thenReturn(java.util.Optional.of(user1));
        User result = userService.findByTokenHash(tokenHash);

        assertEquals("user1", result.getUsername());
        assertEquals("email1", result.getEmail());
        assertEquals("tokenHash1", result.getTokenHash());
        assertEquals("passwordHash1", result.getPasswordHash());
    }

    /**
     * Tests the findByPasswordHash method of the UserService class.
     * Ensures that a list of User objects is returned when a valid password hash is provided.
     */
    @Test
    public void testFindByPasswordHash() {
        String passwordHash = "passwordHash1";
        List<User> mockUserList = List.of(user1, user2);

        when(userRepository.findByPasswordHash(passwordHash)).thenReturn(mockUserList);
        List<User> result = userService.findByPasswordHash(passwordHash);

        assertEquals(2, result.size(), "The size of the result list should be 2");

        assertEquals("user1", result.get(0).getUsername(), "The first user's username should be 'user1'");
        assertEquals("tokenHash1", result.get(0).getTokenHash(), "The first user's token hash should be 'tokenHash1'");

        assertEquals("email2", result.get(1).getEmail(), "The second user's email should be 'email2'");
        assertEquals("passwordHash2", result.get(1).getPasswordHash(), "The second user's password hash should be 'passwordHash2'");
    }

    /**
     * Tests the findByUsernameAndPasswordHash method of the UserService class.
     * Ensures that a User object is returned when a valid username and password hash are provided.
     */
    @Test
    public void testFindByUserNameAndPasswordHash() {
        String username = "user1";
        String passwordHash = "passwordHash1";
        User mockUser = user1;

        when(userRepository.findByUsernameAndPasswordHash(username, passwordHash)).thenReturn(mockUser);
        User result = userService.findByUsernameAndPasswordHash(username, passwordHash);

        assertEquals("user1", result.getUsername(), "The username should be 'user1'");
        assertEquals("email1", result.getEmail(), "The email should be 'email1'");
        assertEquals("tokenHash1", result.getTokenHash(), "The token hash should be 'tokenHash1'");
        assertEquals("passwordHash1", result.getPasswordHash(), "The password hash should be 'passwordHash1'");
    }
}
