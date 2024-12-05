package edu.gsu.cs4350.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.gsu.cs4350.Models.User;
import edu.gsu.cs4350.Repositories.UserRepository;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class PasswordResetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public void initiatePasswordReset(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Email not found"));
        String resetToken = generateToken();

        user.setResetToken(resetToken);
        user.setResetTokenExpiration(LocalDateTime.now().plusMinutes(5));

        userRepository.save(user);

        emailService.sendPasswordResetEmail(email, resetToken);
    }

    public boolean validateResetToken(String email, String token) {
        return userRepository.findByEmail(email)
                .filter(user -> user.isResetTokenValid() && user.getResetToken().equals(token))
                .isPresent();
    }

    public void resetPassword(String email, String token, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Email not found"));

        if (!validateResetToken(email, token)) {
            throw new IllegalArgumentException("Invalid or expired reset token");
        }
        user.setencryptedAccountPassword(newPassword);
        user.setResetToken(null);
        user.setResetTokenExpiration(null);

        userRepository.save(user);
    }

    private String generateToken() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }
}
