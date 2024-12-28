package com.cryptocurrency.data.controller;

import com.cryptocurrency.data.model.User;
import com.cryptocurrency.data.service.LoginRequestService;
import com.cryptocurrency.data.service.TokenRequestService;
import com.cryptocurrency.data.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * The UserController class is a Spring REST controller for managing users.
 * Author: Mouhamadou Ahibou DIALLO
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    /**
     * The UserService class is a Spring service for managing users.
     */
    @Autowired
    private UserService userService;

    /**
     * Creates a new user with the given details.
     *
     * @param user The user object containing the details.
     * @return The created user object.
     */
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (userService.emailExists(user.getEmail())) {
            System.out.println("cet email existe déja.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("cet email existe déja.");
        }
        if (userService.userNameExists(user.getUsername())) {
            System.out.println("ce nom d'utilisateur existe déja.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ce nom d'utilisateur existe déja.");
        }

        try {
            User createdUser = userService.createUser(user);
            System.out.println("user created: " + createdUser);

            if (createdUser == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la création de l'utilisateur.");
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "message", "User created successfully.",
                    "token", createdUser.getTokenHash()
            ));
        } catch (IllegalArgumentException e) {
            System.out.println("error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "error", e.getMessage()
            ));
        }
    }

    /**
     * Updates a user with the given details.
     *
     * @param id          The id of the user to be updated.
     * @param user        The user object containing the updated details.
     * @return The updated user object.
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Logs in a user using their email and password.
     *
     * @param loginRequest The user to be logged in.
     * @return The login token for the user.
     */
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestService loginRequest) {
        try {
            String token = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok("Login successful. Token: " + token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    /**
     * Deletes a user with the given id.
     *
     * @param id the id of the user to be deleted
     * @return a success message if the user is deleted successfully
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    /**
     * Returns a list of all users in the database.
     *
     * @return a list of all users in the database
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    /**
     * Verifies if the provided token for a user is valid.
     *
     * @param tokenRequest The request containing the user's email and token.
     * @return A response indicating whether the token is valid.
     */
    @PostMapping("/verify-token")
    public ResponseEntity<String> verifyToken(@RequestBody TokenRequestService tokenRequest) {
        boolean isValid = userService.verifyToken(tokenRequest.getEmail(), tokenRequest.getToken());
        if (isValid) {
            return ResponseEntity.ok("Token is valid.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token.");
        }
    }

    /**
     * Logs out a user using their email.
     *
     * @param email the email of the user to be logged out
     * @return a success message if the user is logged out successfully
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam String email) {
        userService.logoutUser(email);
        return ResponseEntity.ok("User logged out successfully");
    }
}

