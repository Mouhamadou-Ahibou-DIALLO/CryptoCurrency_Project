package com.cryptocurrency.data.controller;

import com.cryptocurrency.data.model.User;
import com.cryptocurrency.data.service.EmailService;
import com.cryptocurrency.data.service.LoginRequestService;
import com.cryptocurrency.data.service.TokenRequestService;
import com.cryptocurrency.data.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The UserControllerTest class is a JUnit test class for the UserController class.
 * Author: Mouhamadou Ahibou DIALLO
 */
public class UserControllerTest {

    /** The user controller. */
    @InjectMocks
    private UserController userController;

    /** The user service. */
    @Mock
    private UserService userService;

    /** The email service. */
    @Mock
    private EmailService emailService;

    /**
     * Sets up the test environment before each test.
     * Initializes the Mockito annotations.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the createUser method of the UserController class.
     * Ensures that a User object is created successfully and the returned response has the correct values.
     */
    @Test
    public void testCreateUserSuccess() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setUsername("testuser");
        user.setId(1L);
        user.setTokenHash("token123");

        when(userService.emailExists(user.getEmail())).thenReturn(false);
        when(userService.userNameExists(user.getUsername())).thenReturn(false);
        when(userService.createUser(user)).thenReturn(user);

        ResponseEntity<?> response = userController.createUser(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertInstanceOf(Map.class, response.getBody());

        Map<?, ?> responseBody = (Map<?, ?>) response.getBody();
        assertEquals("User created successfully.", responseBody.get("message"));
    }

    /**
     * Tests the createUser method of the UserController class.
     * Ensures that a conflict response is returned when attempting to create
     * a user with an email that already exists in the database.
     */
    @Test
    public void testCreateUserEmailExists() {
        User user = new User();
        user.setEmail("existing@example.com");
        when(userService.emailExists(user.getEmail())).thenReturn(true);

        ResponseEntity<?> response = userController.createUser(user);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("cet email existe déja.", response.getBody());
    }

    /**
     * Tests the updateUser method of the UserController class.
     * Ensures that a User object is updated successfully and the returned response has the correct values.
     */
    @Test
    public void testUpdateUserSuccess() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setUsername("updatedUser");
        user.setPasswordHash("newPasswordHash");
        user.setTokenHash("newTokenHash");

        when(userService.findById(userId)).thenReturn(user);
        when(userService.updateUser(eq(userId), any(User.class))).thenReturn(user);

        System.out.println(userController.updateUser(userId, user));
        ResponseEntity<?> response = userController.updateUser(userId, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Map.class, response.getBody());

        Map<?, ?> responseBody = (Map<?, ?>) response.getBody();
        assertEquals("User updated successfully.", responseBody.get("message"));
    }

    /**
     * Tests the getUserById method of the UserController class.
     * Ensures that a User object is returned successfully with the correct ID
     * when the user is found in the database.
     */
    @Test
    public void testGetUserByIdFound() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(userService.findById(userId)).thenReturn(user);

        ResponseEntity<User> response = userController.getUserById(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    /**
     * Tests the getUserById method of the UserController class.
     * Ensures that a NOT_FOUND status is returned when a user is not found in the database for the given ID.
     */
    @Test
    public void testGetUserByIdNotFound() {
        Long userId = 1L;
        when(userService.findById(userId)).thenReturn(null);

        ResponseEntity<User> response = userController.getUserById(userId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    /**
     * Tests the deleteUser method of the UserController class.
     * Ensures that a successful response is returned when deleting a user with a valid ID.
     */
    @Test
    public void testDeleteUser() {
        Long userId = 1L;
        doNothing().when(userService).deleteById(userId);

        ResponseEntity<String> response = userController.deleteUser(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User deleted successfully", response.getBody());
    }

    /**
     * Tests the getAllUsers method of the UserController class.
     * Ensures that a successful response is returned when retrieving all users.
     * The response should contain a list of User objects.
     */
    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);

        List<User> users = Arrays.asList(user1, user2);
        when(userService.findAll()).thenReturn(users);

        ResponseEntity<List<User>> response = userController.getAllUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    /**
     * Tests the forgotPassword method of the UserController class.
     * Ensures that a successful response is returned when the email is found in the database.
     * The response should contain a map with a success message.
     */
    @Test
    public void testForgotPasswordSuccess() {
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);
        user.setTokenHash("token123");

        when(userService.findByEmail(email)).thenReturn(user);

        Map<String, String> request = new HashMap<>();
        request.put("email", email);

        doNothing().when(emailService).sendEmail(eq(email), anyString(), anyString());

        ResponseEntity<?> response = userController.forgotPassword(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertInstanceOf(Map.class, response.getBody());

        Map<?, ?> responseBody = (Map<?, ?>) response.getBody();
        assertEquals("Un email de réinitialisation a été envoyé.", responseBody.get("message"));
    }

    /**
     * Tests the forgotPassword method of the UserController class.
     * Ensures that a not found response is returned when the email is not found in the database.
     * The response should contain a map with an error message.
     */
    @Test
    public void testForgotPasswordEmailNotFound() {
        String email = "notfound@example.com";
        when(userService.findByEmail(email)).thenReturn(null);

        Map<String, String> request = new HashMap<>();
        request.put("email", email);

        ResponseEntity<?> response = userController.forgotPassword(request);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertInstanceOf(Map.class, response.getBody());

        Map<?, ?> responseBody = (Map<?, ?>) response.getBody();
        assertEquals("Email non trouvé.", responseBody.get("error"));
    }

    /**
     * Tests the updateToken method of the UserController class.
     * Ensures that a user's token is updated successfully and the response contains the correct token.
     * Verifies that the updateToken service is called exactly once with the specified user ID.
     */
    @Test
    public void testUpdateTokenSuccess() {
        Long userId = 1L;
        User mockUser = new User();
        mockUser.setTokenHash("newTokenHash");

        when(userService.updateToken(userId)).thenReturn(mockUser);
        ResponseEntity<?> response = userController.updateToken(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(((Map<?, ?>) Objects.requireNonNull(response.getBody())).containsKey("token"));
        verify(userService, times(1)).updateToken(userId);
    }

    /**
     * Tests the loginUser method of the UserController class.
     * Ensures that a user is authenticated successfully and the returned response
     * contains a success message along with the login token.
     * Verifies that the authenticateUser service is called exactly once with the specified email and password.
     */
    @Test
    public void testLoginUserSuccess() {
        String email = "test@example.com";
        String password = "password123";
        String token = "loginToken";

        LoginRequestService loginRequest = new LoginRequestService(email, password);
        when(userService.authenticateUser(email, password)).thenReturn(token);
        ResponseEntity<String> response = userController.loginUser(loginRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).contains("Login successful"));
        verify(userService, times(1)).authenticateUser(email, password);
    }

    /**
     * Tests the verifyToken method of the UserController class.
     * Ensures that a token is verified successfully and the returned response
     * contains a success message along with the user's details.
     * Verifies that the verifyToken service is called exactly once with the specified email and token.
     */
    @Test
    public void testVerifyTokenSuccess() {
        String email = "test@example.com";
        String token = "validToken";

        TokenRequestService tokenRequest = new TokenRequestService(email, token);
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("testUser");
        mockUser.setEmail(email);
        mockUser.setTokenHash(token);

        when(userService.verifyToken(email, token)).thenReturn(true);
        when(userService.findByEmail(email)).thenReturn(mockUser);
        ResponseEntity<?> response = userController.verifyToken(tokenRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(((Map<?, ?>) Objects.requireNonNull(response.getBody())).containsKey("id"));
        verify(userService, times(1)).verifyToken(email, token);
    }

    /**
     * Tests the logoutUser method of the UserController class.
     * Ensures that a user is logged out successfully and the returned response
     * contains a success message.
     * Verifies that the logoutUser service is called exactly once with the specified email.
     */
    @Test
    public void testLogoutUserSuccess() {
        String email = "test@example.com";
        doNothing().when(userService).logoutUser(email);
        ResponseEntity<String> response = userController.logout(email);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User logged out successfully", response.getBody());
        verify(userService, times(1)).logoutUser(email);
    }

    /**
     * Tests the resetPassword method of the UserController class.
     * Ensures that a password is reset successfully and the returned response
     * contains a success message.
     * Verifies that the findByTokenHash and save services are called exactly once with the specified token and user.
     */
    @Test
    public void testResetPasswordSuccess() {
        String token = "resetToken";
        String newPassword = "newPassword123";
        Map<String, String> request = new HashMap<>();
        request.put("token", token);
        request.put("passwordHash", newPassword);

        User mockUser = new User();
        mockUser.setTokenHash(token);

        when(userService.findByTokenHash(token)).thenReturn(mockUser);
        ResponseEntity<?> response = userController.resetPassword(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(((Map<?, ?>) Objects.requireNonNull(response.getBody())).containsKey("message"));
        verify(userService, times(1)).findByTokenHash(token);
        verify(userService, times(1)).save(mockUser);
    }

    /**
     * Tests the upgradeToPremium method of the UserController class.
     * Ensures that the user is upgraded to premium successfully and the returned response
     * contains a success message.
     * Verifies that the updateUserStatusToPremium service is called exactly once with the specified user ID.
     */
    @Test
    public void testUpgradeToPremiumSuccess() {
        Long userId = 1L;
        Map<String, Long> request = Map.of("userId", userId);

        when(userService.updateUserStatusToPremium(userId)).thenReturn(true);
        ResponseEntity<Map<String, String>> response = userController.upgradeToPremium(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("true", Objects.requireNonNull(response.getBody()).get("success"));
        verify(userService, times(1)).updateUserStatusToPremium(userId);
    }

    /**
     * Tests the downgradeToStandard method of the UserController class.
     * Ensures that a user is downgraded to standard successfully and the returned response
     * contains a success message.
     * Verifies that the updateUserStatusToFree service is called exactly once with the specified user ID.
     */
    @Test
    public void testDowngradeToStandardSuccess() {
        Long userId = 1L;
        Map<String, Long> request = Map.of("userId", userId);

        when(userService.updateUserStatusToFree(userId)).thenReturn(true);
        ResponseEntity<Map<String, String>> response = userController.downgradeToStandard(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("true", Objects.requireNonNull(response.getBody()).get("success"));
        verify(userService, times(1)).updateUserStatusToFree(userId);
    }
}
