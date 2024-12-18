package com.cryptocurrency.data.repository;

import com.cryptocurrency.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by its username.
     *
     * @param username the username to find the user for
     * @return a list of users with the given username
     */
    User findByUsername(String username);

    /**
     * Find a user by its token hash.
     *
     * @param tokenHash the token hash to find the user for
     * @return a list of users with the given token hash
     */
    List<User> findByTokenHash(String tokenHash);

    /**
     * Find a user by its email address.
     *
     * @param email the email address to find the user for
     * @return a list of users with the given email address
     */
    Optional<User> findByEmail(String email);

    /**
     * Find a user by its password hash.
     *
     * @param passwordHash the password hash to find the user for
     * @return a list of users with the given password hash
     */
    List<User> findByPasswordHash(String passwordHash);

    /**
     * Find a user by its username and password hash.
     *
     * @param username      the username to find the user for
     * @param passwordHash the password hash to find the user for
     * @return the user with the given username and password hash
     */
    User findByUsernameAndPasswordHash(String username, String passwordHash);
}
