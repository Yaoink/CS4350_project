package edu.gsu.cs4350.Services;

import org.springframework.stereotype.Service;

@Service
public class PasswordGeneratorService {
    public String generateRandomPassword(int length, boolean includeUppercase, boolean includeNumbers,
            boolean includeSymbols) {
        // This implementation will always return a fixed string,
        // which will likely fail most test cases
        return "FixedPassword123!";
    }
}
