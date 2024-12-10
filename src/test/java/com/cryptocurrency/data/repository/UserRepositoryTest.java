package com.cryptocurrency.data.repository;

import com.cryptocurrency.data.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;
    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void setUp() {
        user1 = new User(1L, "user1",  "email1", "tokenHash1", "passwordHash1");
        user2 = new User(2L, "user2", "email2", "tokenHash2", "passwordHash2");
        user3 = new User(3L, "user3", "email3", "tokenHash3", "passwordHash3");
    }

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

    @Test
    public void testFindByUsername() {
        String username = "user2";
        List<User> mockUserList = List.of(user2);

        when(userRepository.findByUsername(username)).thenReturn(mockUserList);
        List<User> result = userRepository.findByUsername(username);

        assertEquals(1, result.size());
        assertEquals("user2", result.get(0).getUsername());
        assertEquals("email2", result.get(0).getEmail());
        assertEquals("tokenHash2", result.get(0).getTokenHash());
        assertEquals("passwordHash2", result.get(0).getPasswordHash());

        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(java.util.Optional.of(user1));
        User result = userRepository.findById(id).get();

        assertEquals("user1", result.getUsername());
        assertEquals("email1", result.getEmail());
        assertEquals("tokenHash1", result.getTokenHash());
        assertEquals("passwordHash1", result.getPasswordHash());

        verify(userRepository, times(1)).findById(id);
    }

    @Test
    public void testFindByEmail() {
        String email = "email2";
        List<User> mockUserList = List.of(user2);

        when(userRepository.findByEmail(email)).thenReturn(mockUserList);
        List<User> result = userRepository.findByEmail(email);

        assertEquals(1, result.size());
        assertEquals("user2", result.get(0).getUsername());
        assertEquals("email2", result.get(0).getEmail());
        assertEquals("tokenHash2", result.get(0).getTokenHash());
        assertEquals("passwordHash2", result.get(0).getPasswordHash());

        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    public void testSave() {
        when(userRepository.save(user1)).thenReturn(user1);
        User result = userRepository.save(user1);

        assertEquals("user1", result.getUsername());
        assertEquals("email1", result.getEmail());
        assertEquals("tokenHash1", result.getTokenHash());
        assertEquals("passwordHash1", result.getPasswordHash());

        verify(userRepository, times(1)).save(user1);
    }

    @Test
    public void testDelete() {
        userRepository.delete(user1);
        verify(userRepository, times(1)).delete(user1);
    }

    @Test
    public void testFindByTokenHash() {
        String tokenHash = "tokenHash1";
        List<User> mockUserList = List.of(user1);

        when(userRepository.findByTokenHash(tokenHash)).thenReturn(mockUserList);
        List<User> result = userRepository.findByTokenHash(tokenHash);

        assertEquals(1, result.size());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals("email1", result.get(0).getEmail());
        assertEquals("tokenHash1", result.get(0).getTokenHash());
        assertEquals("passwordHash1", result.get(0).getPasswordHash());

        verify(userRepository, times(1)).findByTokenHash(tokenHash);
    }

    @Test
    public void testFindByPasswordHash() {
        String passwordHash = "passwordHash1";
        List<User> mockUserList = List.of(user1);

        when(userRepository.findByPasswordHash(passwordHash)).thenReturn(mockUserList);
        List<User> result = userRepository.findByPasswordHash(passwordHash);

        assertEquals(1, result.size());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals("email1", result.get(0).getEmail());
        assertEquals("tokenHash1", result.get(0).getTokenHash());
        assertEquals("passwordHash1", result.get(0).getPasswordHash());

        verify(userRepository, times(1)).findByPasswordHash(passwordHash);
    }

    @Test
    public void findByUsernameAndPasswordHash() {
        String username = "user1";
        String passwordHash = "passwordHash1";
        when(userRepository.findByUsernameAndPasswordHash(username, passwordHash)).thenReturn(user1);
        User result = userRepository.findByUsernameAndPasswordHash(username, passwordHash);
        assertEquals(user1, result);
    }
}
