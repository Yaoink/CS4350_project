package edu.gsu.cs4350.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.gsu.cs4350.Models.User;
import edu.gsu.cs4350.Repositories.UserRepository;
import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already registered");
        }
        User user = new User();
        user.setemail(email);
        user.setencryptedAccountPassword(password);
        user.settimeCreated(LocalDateTime.now());
        user.setlastLogin(LocalDateTime.now());

        return userRepository.save(user);
    }

    public void setTestString(String email, String testString) {
        userRepository.findByEmail(email)
                .ifPresent(user -> {
                    user.setTestString(testString);
                    userRepository.save(user);
                });
    }

    public boolean authenticateUser(String email, String password) {
        return userRepository.findByEmail(email)
                .map(user -> password.equals(user.getencryptedAccountPassword()))
                .orElse(false);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElse(null);
    }

    public void updateLastLogin(String email) {
        userRepository.findByEmail(email)
                .ifPresent(user -> {
                    user.setlastLogin(LocalDateTime.now());
                    userRepository.save(user);
                });
    }

}