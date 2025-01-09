package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.User;
import com.cryptocurrency.data.repository.UserRepository;
import com.cryptocurrency.data.security.EncodedPassword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

    /**
     * Tests the emailExists method of the UserService class.
     * Ensures that the method returns true if the provided email address exists in the database.
     */
    @Test
    public void testEmailExists() {
        String email = "email1";
        when(userRepository.findByEmail(email)).thenReturn(java.util.Optional.of(user1));
        boolean result = userService.emailExists(email);
        assertTrue(result);
    }

    /**
     * Tests the emailExists method of the UserService class.
     * Ensures that the method returns false if the provided email address does not exist in the database.
     */
    @Test
    public void testEmailExistsFalse() {
        String email = "email1";
        when(userRepository.findByEmail(email)).thenReturn(java.util.Optional.empty());
        boolean result = userService.emailExists(email);
        assertFalse(result);
    }

    /**
     * Tests the userNameExists method of the UserService class.
     * Ensures that the method returns true if the provided username exists in the database.
     */
    @Test
    public void testUserNameExists() {
        String username = "user1";
        when(userRepository.findByUsername(username)).thenReturn(java.util.Optional.of(user1));
        boolean result = userService.userNameExists(username);
        assertTrue(result);
    }

    /**
     * Tests the createUser method of the UserService class.
     * Ensures that a User object is saved correctly and the createUser method returns the saved User object.
     */
    @Test
    public void testCreateUser() {
        User user = new User(5L,"username", "email", "tokenHash", "Password2024@");
        when(userRepository.save(user)).thenReturn(user);
        User result = userService.createUser(user);
        assertEquals(user, result);
    }

    /**
     * Tests the updateUser method of the UserService class.
     * Ensures that a User object is updated correctly and the updateUser method returns the updated User object.
     */
    @Test
    public void testUpdateUser() {
        User user = new User(5L,"username1", "email", "tokenHash", "Password2024@");
        when(userRepository.findById(user.getId())).thenReturn(java.util.Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        User result = userService.updateUser(user.getId(), user);
        assertEquals(user, result);
    }

    /**
     * Tests the updateToken method of the UserService class.
     * Ensures that a user's token hash is updated correctly.
     * Verifies that the findById and save methods of the UserRepository are called with the correct arguments.
     * Verifies that the returned User object has a non-null token hash that is not equal to the original token hash.
     */
    @Test
    public void testUpdateToken() {
        User user = new User(5L,"username1", "email", "tokenHash", "Password2024@");
        when(userRepository.findById(user.getId())).thenReturn(java.util.Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        User result = userService.updateToken(user.getId());

        verify(userRepository).findById(user.getId());
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        User capturedUser = userCaptor.getValue();
        assertNotNull(capturedUser.getTokenHash());
        assertNotEquals("tokenHash", capturedUser.getTokenHash());

        assertEquals(result, capturedUser);
    }

    /**
     * Tests the findByEmailAndPasswordHash method of the UserService class.
     * Ensures that a User object is returned when a valid email and password hash are provided.
     */
    @Test
    public void testFinfByEmailAndPasswordHash() {
        User user = new User(5L,"username1", "email", "tokenHash", "Password2024@");
        when(userRepository.findByEmailAndPasswordHash(user.getEmail(), user.getPasswordHash())).thenReturn(Optional.of(user));
        Optional<User> result = userService.findByEmailAndPasswordHash(user.getEmail(), user.getPasswordHash());
        assertNotNull(result);
    }

    /**
     * Tests the findByEmailAndTokenHash method of the UserService class.
     * Ensures that a User object is returned when a valid email and token hash are provided.
     */
    @Test
    public void testFindByEmailAndTokenHash() {
        User user = new User(5L,"username1", "email", "tokenHash", "Password2024@");
        when(userRepository.findByEmailAndTokenHash(user.getEmail(), user.getTokenHash())).thenReturn(Optional.of(user));
        Optional<User> result = userService.findByEmailAndTokenHash(user.getEmail(), user.getTokenHash());
        assertNotNull(result);
    }

    /**
     * Tests the authenticateUser method of the UserService class.
     * Ensures that the correct token hash is returned when a valid email and password are provided.
     * Verifies that findByEmail and findByEmailAndPasswordHash are called once each.
     */
    @Test
    public void testAuthenticationUser() {
        User user = new User(5L, "username1", "email", "tokenHash", "encodedPassword"); // mot de passe encodé directement
        String email = user.getEmail();
        String password = "Password2024@";
        String tokenHash = user.getTokenHash();
        String encodedPassword = user.getPasswordHash();

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(userRepository.findByEmailAndPasswordHash(email, encodedPassword)).thenReturn(Optional.of(user));

        try (MockedStatic<EncodedPassword> mockedPassword = mockStatic(EncodedPassword.class)) {
            mockedPassword.when(() -> EncodedPassword.isRightPassword(password, encodedPassword)).thenReturn(true);

            String returnedToken = userService.authenticateUser(email, password);

            assertEquals(tokenHash, returnedToken);
            verify(userRepository).findByEmail(email);
            verify(userRepository).findByEmailAndPasswordHash(email, encodedPassword);
            verifyNoMoreInteractions(userRepository);
        }
    }


    /**
     * Tests the verifyToken method of the UserService class.
     * Ensures that the method returns true when a valid email and token are provided.
     * Verifies that the findByEmail and findByEmailAndTokenHash methods of the UserRepository are called.
     */
    @Test
    public void testVerifyToken() {
        User user = new User(5L,"username1", "email", "tokenHash", "Password2024@");
        String email = user.getEmail();
        String token = user.getTokenHash();

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(userRepository.findByEmailAndTokenHash(email, token)).thenReturn(Optional.of(user));

        boolean result = userService.verifyToken(email, token);

        assertTrue(result);
        verify(userRepository).findByEmail(email);
        verify(userRepository).findByEmailAndTokenHash(email, token);
    }

    /**
     * Tests the updateUserStatusToPremium and updateUserStatusToFree methods of the UserService class.
     * Ensures that the methods update the status of a user correctly and return true if the status was updated.
     */
    @Test
    public void testStatutUser() {
        User user = new User(5L,"username1", "email", "tokenHash", "Password2024@");
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        user.setStatut("normal");
        when(userRepository.save(user)).thenReturn(user);
        boolean result = userService.updateUserStatusToPremium(user.getId());
        assertTrue(result);

        user.setStatut("premium");
        when(userRepository.save(user)).thenReturn(user);
        boolean result2 = userService.updateUserStatusToFree(user.getId());
        assertTrue(result2);
    }

    /**
     * Tests the logoutUser method of the UserService class.
     * Ensures that the method clears the email and token hash of a user correctly.
     */
    @Test
    public void testLogoutUser() {
        User user = new User(5L,"username1", "email", "tokenHash", "Password2024@");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        userService.logoutUser(user.getEmail());
        assertNull(user.getEmail());
        assertNull(user.getTokenHash());
    }
}
