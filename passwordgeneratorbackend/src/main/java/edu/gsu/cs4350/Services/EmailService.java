package edu.gsu.cs4350.Services;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendPasswordResetEmail(String email, String resetToken);
    // Other email-related methods
}
