package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.User;
import com.cryptocurrency.data.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The service for User objects.
 * Author: Mouhamadou Ahibou DIALLO
 */
@Service
public class UserService {

    /**
     * The repository for User objects.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * The password encoder.
     */

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Retrieve all users from the repository.
     *
     * @return a list of all User objects.
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Find a user by its username.
     *
     * @param username the username to find the user for
     * @return a User object with the given username, or an empty optional if none is found
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Find a user by its email.
     *
     * @param email the email to find the user for
     * @return a User object with the given email, or an empty optional if none is found
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    /**
     * Find a user by its id.
     *
     * @param id the id to find the user for
     * @return a User object with the given id, or null if none is found
     */
    public User findById(Long id) {
        Optional<User> result = userRepository.findById(id);
        return result.orElse(null);
    }

    /**
     * Find a user by its token hash.
     *
     * @param tokenHash the token hash to find the user for
     * @return a User object with the given token hash
     * @throws RuntimeException if no user is found with the given token hash
     */
    public User findByTokenHash(String tokenHash) {
        return userRepository.findByTokenHash(tokenHash)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Find users by their password hash.
     *
     * @param passwordHash the password hash to search for
     * @return a list of users with the given password hash
     */
    public List<User> findByPasswordHash(String passwordHash) {
        return userRepository.findByPasswordHash(passwordHash);
    }

    /**
     * Find a user by its username and password hash.
     *
     * @param username      the username to find the user for
     * @param passwordHash the password hash to find the user for
     * @return a User object with the given username and password hash, or null if none is found
     */
    public User findByUsernameAndPasswordHash(String username, String passwordHash) {
        return userRepository.findByUsernameAndPasswordHash(username, passwordHash);
    }

    /**
     * Saves a User object in the repository.
     *
     * @param user the User object to save
     * @return the saved User object
     */
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Deletes a user by its id.
     *
     * @param id the id of the user to be deleted
     */
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Creates a new user, encoding their password and generating a token.
     *
     * @param user the User object to be created
     * @return the created User object
     */
    public User createUser(User user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));

        String token = UUID.randomUUID().toString();
        user.setTokenHash(passwordEncoder.encode(token));

        return userRepository.save(user);
    }

    /**
     * Updates a user with the given details.
     *
     * @param id          the id of the user to be updated
     * @param userDetails the User object containing the updated details
     * @return the updated User object
     */
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPasswordHash(passwordEncoder.encode(userDetails.getPasswordHash()));

        return userRepository.save(user);
    }

    /**
     * Authenticates a user using their email and password.
     *
     * @param email    the email of the user to be authenticated
     * @param password the password of the user to be authenticated
     * @return the token hash of the user
     */
    public String authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (passwordEncoder.matches(password, user.getPasswordHash())) {
            return user.getTokenHash();
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }

    /**
    /**
     * Verifies if the provided token matches the stored token hash for the user with the given email.
     *
     * @param email the email of the user whose token is to be verified
     * @param token the token to verify
     * @return true if the token is valid, false otherwise
     * @throws RuntimeException if the user is not found
     */
    public boolean verifyToken(String email, String token) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return passwordEncoder.matches(token, user.getTokenHash());
    }


    /**
     * Logs out a user with the given email.
     *
     * @param email the email of the user to be logged out
     */
    public void logoutUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setTokenHash(null);
    }
}
