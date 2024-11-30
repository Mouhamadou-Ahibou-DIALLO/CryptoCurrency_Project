import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by its username.
     *
     * @param username the username to find the user for
     * @return a list of users with the given username
     */
    List<User> findByUsername(String username);

    /**
     * Find a user by its token hash.
     *
     * @param token_hash the token hash to find the user for
     * @return a list of users with the given token hash
     */
    List<User> findByToken_hash(String token_hash);

    /**
     * Find a user by its email address.
     *
     * @param email the email address to find the user for
     * @return a list of users with the given email address
     */
    List<User> findByEmail(String email);

    /**
     * Find a user by its password hash.
     *
     * @param password_hash the password hash to find the user for
     * @return a list of users with the given password hash
     */
    List<User> findByPassword_hash(String password_hash);

    /**
     * Find a user by its username and password hash.
     *
     * @param username      the username to find the user for
     * @param passwordHash the password hash to find the user for
     * @return the user with the given username and password hash
     */
    User findByUsernameAndPassword_hash(String username, String passwordHash);
}
