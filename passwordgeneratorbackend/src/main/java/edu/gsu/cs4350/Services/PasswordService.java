package edu.gsu.cs4350.Services;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.gsu.cs4350.Models.Password;
import edu.gsu.cs4350.Repositories.PasswordRepository;
import edu.gsu.cs4350.Repositories.WebsiteRepository;

@Service
public class PasswordService {
    @Autowired
    private PasswordRepository passwordRepository;
    @Autowired
    private WebsiteRepository websiteRepository;

    public Password savePassword(String email, String websiteUrl, String username, String password);

    public List<Password> getPasswordsForUser(String email) {
        // This calls the findByEmail method from the PasswordRepository
        // It will return an empty list based on our current implementation
        return passwordRepository.findByEmail(email);
    }

    public Password updatePassword(int passwordId, String newPassword);

    public void deletePassword(int passwordId);
    // Other password-related methods
}