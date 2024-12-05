package edu.gsu.cs4350.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.gsu.cs4350.Models.Password;
import edu.gsu.cs4350.Repositories.PasswordRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PasswordService {

    @Autowired
    private PasswordRepository passwordRepository;

    public Password savePassword(String email, String websiteUrl, String websiteName, String username,
            String plainTextPassword) {
        Password password = new Password();
        password.setencryptedPassword(plainTextPassword);
        password.setemail(email);
        password.setwebsiteName(websiteName);
        password.seturl(websiteUrl);
        password.setusername(username);
        password.settimeCreated(LocalDateTime.now());
        password.setlastModified(LocalDateTime.now());

        return passwordRepository.save(password);
    }

    public List<Password> getPasswordsForUser(String email) {
        return passwordRepository.findByEmail(email);
    }

    public Password updatePassword(int passwordId, String newPlainTextPassword, String websiteUrl,
            String websiteName, String username) {
        Password password = passwordRepository.findById(passwordId)
                .orElseThrow(() -> new IllegalArgumentException("Password not found"));

        password.setencryptedPassword(newPlainTextPassword);
        password.setlastModified(LocalDateTime.now());
        password.seturl(websiteUrl);
        password.setwebsiteName(websiteName);
        return passwordRepository.save(password);
    }

    public void deletePassword(int passwordId) {
        Password password = passwordRepository.findById(passwordId)
                .orElseThrow(() -> new IllegalArgumentException("Password not found"));

        passwordRepository.delete(password);
    }

    public Password getPasswordById(int passwordId) {
        return passwordRepository.findById(passwordId)
                .orElseThrow(() -> new IllegalArgumentException("Password not found"));
    }
}