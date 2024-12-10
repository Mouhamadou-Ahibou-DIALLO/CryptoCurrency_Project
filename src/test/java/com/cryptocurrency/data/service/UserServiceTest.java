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

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user1;
    private User user2;

    @BeforeEach
    public void setUp() {
        user1 = new User(1L, "user1",  "email1", "tokenHash1", "passwordHash1");
        user2 = new User(2L, "user2", "email2", "tokenHash2", "passwordHash2");
    }

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

    @Test
    public void testFindByEmail() {
        String email = "email1";
        List<User> mockUserList = List.of(user1, user2);

        when(userRepository.findByEmail(email)).thenReturn(mockUserList);
        List<User> result = userService.findByEmail(email);

        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals("email2", result.get(1).getEmail());
        assertEquals("tokenHash1", result.get(0).getTokenHash());
        assertEquals("passwordHash2", result.get(1).getPasswordHash());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        User mockUser = user1;

        when(userRepository.findById(id)).thenReturn(java.util.Optional.of(mockUser));
        User result = userService.findById(id);

        assertEquals("user1", result.getUsername());
        assertEquals("email1", result.getEmail());
        assertEquals("tokenHash1", result.getTokenHash());
        assertEquals("passwordHash1", result.getPasswordHash());
    }

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

    @Test
    public void testDeleteById() {
        Long id = 1L;

        userService.deleteById(id);

        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    public void testFindByUsername() {
        String username = "user1";
        List<User> mockUserList = List.of(user1, user2);

        when(userRepository.findByUsername(username)).thenReturn(mockUserList);
        List<User> result = userService.findByUsername(username);

        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals("email2", result.get(1).getEmail());
        assertEquals("tokenHash1", result.get(0).getTokenHash());
        assertEquals("passwordHash2", result.get(1).getPasswordHash());
    }

    @Test
    public void testFindByTokenHash() {
        String tokenHash = "tokenHash1";
        List<User> mockUserList = List.of(user1, user2);

        when(userRepository.findByTokenHash(tokenHash)).thenReturn(mockUserList);
        List<User> result = userService.findByTokenHash(tokenHash);

        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals("email2", result.get(1).getEmail());
        assertEquals("tokenHash1", result.get(0).getTokenHash());
        assertEquals("passwordHash2", result.get(1).getPasswordHash());
    }

    @Test
    public void testFindByPasswordHash() {
        String passwordHash = "passwordHash1";
        List<User> mockUserList = List.of(user1, user2);

        when(userRepository.findByPasswordHash(passwordHash)).thenReturn(mockUserList);
        List<User> result = userService.findByPasswordHash(passwordHash);

        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals("email2", result.get(1).getEmail());
        assertEquals("tokenHash1", result.get(0).getTokenHash());
        assertEquals("passwordHash2", result.get(1).getPasswordHash());
    }

    @Test
    public void testFindByUserNameAndPasswordHash() {
        String username = "user1";
        String passwordHash = "passwordHash1";
        User mockUser = user1;

        when(userRepository.findByUsernameAndPasswordHash(username, passwordHash)).thenReturn(mockUser);
        User result = userService.findByUsernameAndPasswordHash(username, passwordHash);

        assertEquals("user1", result.getUsername());
        assertEquals("email1", result.getEmail());
        assertEquals("tokenHash1", result.getTokenHash());
        assertEquals("passwordHash1", result.getPasswordHash());
    }
}
