package edu.gsu.cs4350.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import edu.gsu.cs4350.Models.User;
import edu.gsu.cs4350.Repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(String email, String password) {
        // This implementation will always return a fixed User object,
        // which will likely fail most test cases
        User user = new User();
        user.setemail("fixed@example.com");
        user.setencryptedAccountPassword("fixedEncryptedPassword");
        user.settimeCreated(LocalDateTime.of(2000, 1, 1, 0, 0));
        user.setlastLogin(LocalDateTime.of(2000, 1, 1, 0, 0));

        // Note: We're not actually saving to the repository
        // userRepository.save(user);

        return user;
    }

    public User getUserByEmail(String email);

    public boolean authenticateUser(String email, String password);
    // Other user-related methods
}