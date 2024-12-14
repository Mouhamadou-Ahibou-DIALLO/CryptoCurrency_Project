package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.User;
import com.cryptocurrency.data.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findByEmail(String email) {
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
}
