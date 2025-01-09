package com.cryptocurrency.data.repository;

import com.cryptocurrency.data.model.Transaction;
import com.cryptocurrency.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The TransactionRepository interface is a Spring Data JPA repository for managing transaction data.
 * TransactionRepository interface.
 * Author: Mouhamadou Ahibou DIALLO
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Returns a list of transactions associated with the given user.
     *
     * @param user The user object to find transactions for
     * @return A list of transactions associated with the given user
     */
    List<Transaction> findByUser(User user);
}
