package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.User;
import com.cryptocurrency.data.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(Long id) {
        Optional<User> result = userRepository.findById(id);
        return result.orElse(null);
    }

    public List<User> findByTokenHash(String tokenHash) {
        return userRepository.findByTokenHash(tokenHash);
    }

    public List<User> findByPasswordHash(String passwordHash) {
        return userRepository.findByPasswordHash(passwordHash);
    }

    public User findByUsernameAndPasswordHash(String username, String passwordHash) {
        return userRepository.findByUsernameAndPasswordHash(username, passwordHash);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User createUser(User user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        String token = UUID.randomUUID().toString();
        user.setTokenHash(passwordEncoder.encode(token));
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPasswordHash(passwordEncoder.encode(userDetails.getPasswordHash()));
        return userRepository.save(user);
    }

    public String authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (passwordEncoder.matches(password, user.getPasswordHash())) {
            return user.getTokenHash();
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }

    public boolean verifyToken(String email, String token) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return passwordEncoder.matches(token, user.getTokenHash());
    }


    public void logoutUser() {
        // Exemple : Invalider le token ou gérer la session
    }
}
