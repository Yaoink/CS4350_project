package com.csc4350.passwordgen;

import com.csc4350.passwordgen.Passwords;
import com.csc4350.passwordgen.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasswordService {

    @Autowired
    private PasswordRepository passwordRepository;

    // Get the list of saved passwords for a user
    public List<Password> getSavedPasswordsByUserId(Long userId) {
        return passwordRepository.findByUserId(userId);
    }

    // Get a specific password by its ID
    public Optional<Password> getPasswordById(Long passwordId) {
        return passwordRepository.findById(passwordId);
    }

    // Update a password
    public boolean updatePassword(Long passwordId, String newPassword) {
        Optional<Password> passwordOptional = passwordRepository.findById(passwordId);
        if (passwordOptional.isPresent()) {
            Password password = passwordOptional.get();
            password.setPassword(newPassword);
            passwordRepository.save(password);
            return true;
        }
        return false;
    }
}
