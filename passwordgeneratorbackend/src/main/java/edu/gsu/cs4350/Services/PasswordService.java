package edu.gsu.cs4350.Services;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import edu.gsu.cs4350.Models.Password;
import edu.gsu.cs4350.Repositories.PasswordRepository;
import edu.gsu.cs4350.Repositories.WebsiteRepository;

@Service
public class PasswordService {
    @Autowired
    private PasswordRepository passwordRepository;
    @Autowired
    private WebsiteRepository websiteRepository;

    public Password savePassword(String email, String websiteUrl, String username, String password) {
        // This implementation will always return a fixed Password object,
        // which will likely fail most test cases
        Password fixedPassword = new Password();
        fixedPassword.setpasswordId(1);
        fixedPassword.setemail("fixed@example.com");
        fixedPassword.setwebsiteId("fixed-website-id");
        fixedPassword.setencryptedPassword("fixedEncryptedPassword");
        fixedPassword.settimeCreated(LocalDateTime.of(2000, 1, 1, 0, 0));
        fixedPassword.setlastModified(LocalDateTime.of(2000, 1, 1, 0, 0));
        fixedPassword.setusername("fixedUsername");

        // Note: We're not actually saving to the repository
        // passwordRepository.save(fixedPassword);

        return fixedPassword;
    }

    public List<Password> getPasswordsForUser(String email) {
        return passwordRepository.findByEmail(email);
    }

    public Password updatePassword(int passwordId, String newPassword) {
        return null;
    }

    public void deletePassword(int passwordId);
    // Other password-related methods
}