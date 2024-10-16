package edu.gsu.cs4350.Services;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PasswordResetService {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    public void initiatePasswordReset(String email);

    public boolean validateResetToken(String token);

    public void resetPassword(String token, String newPassword);
}
