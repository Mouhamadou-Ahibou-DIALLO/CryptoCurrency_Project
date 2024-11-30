import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

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

    public List<User> findByToken_hash(String token_hash) {
        return userRepository.findByToken_hash(token_hash);
    }

    public List<User> findByPassword_hash(String password_hash) {
        return userRepository.findByPassword_hash(password_hash);
    }

    public User findByUsernameAndPassword_hash(String username, String password_hash) {
        return userRepository.findByUsernameAndPassword_hash(username, password_hash);
    }

}
