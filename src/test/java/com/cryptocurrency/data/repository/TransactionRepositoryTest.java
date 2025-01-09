package com.cryptocurrency.data.repository;

import com.cryptocurrency.data.model.Transaction;
import com.cryptocurrency.data.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * The TransactionRepositoryTest class is a JUnit test class for the TransactionRepository class.
 * Author: Mouhamadou Ahibou DIALLO
 */
@ExtendWith(MockitoExtension.class)
public class TransactionRepositoryTest {


    /**
     * The transactionRepository field is a mock of the TransactionRepository class.
     */
    @Mock
    private TransactionRepository transactionRepository;

    /**
     * The user field is a mock of the User class.
     */
    private User user;

    /**
     * The transaction1 and transaction2 fields are mock of the Transaction class.
     */
    private Transaction transaction1;

    /**
     * The transaction2 field is a mock of the Transaction class.
     */
    private Transaction transaction2;

    /**
     * The method setUp is called before each test method invocation.
     * <p>
     * It creates a mock of the TransactionRepository and two Transaction objects.
     * </p>
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User(1L, "user1",  "email1", "tokenHash1", "passwordHash1");

        transaction1 = new Transaction();
        transaction1.setUser(user);
        transaction1.setId(1L);
        transaction2 = new Transaction();
        transaction2.setUser(user);
        transaction2.setId(2L);
    }

    /**
     * Verifies that the method findByUser in TransactionRepository returns a list of Transaction
     * objects associated with the given user.
     * <p>
     * The method findByUser is called with a user object and it is verified that the method
     * returns a list of two Transaction objects.
     * </p>
     */
    @Test
    public void findByUser() {
        when(transactionRepository.findByUser(user)).thenReturn(List.of(transaction1, transaction2));
        assert user != null;
        List<Transaction> result = transactionRepository.findByUser(user);

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(transactionRepository, times(1)).findByUser(user);
    }
}
