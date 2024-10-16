package edu.gsu.cs4350.Services;

import org.springframework.stereotype.Service;

@Service
public class PasswordGeneratorService {
    public String generateRandomPassword(int length, boolean includeUppercase, boolean includeNumbers,
            boolean includeSymbols);
    // Other password generation methods
}
