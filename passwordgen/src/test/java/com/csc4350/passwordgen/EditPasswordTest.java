package com.csc4350.passwordgen;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import java.util.HashMap;
import java.util.Map;

//eric's test
public class EditPasswordTest {
    private Map<String, Password> savedPasswords;
    private Map<String, Account> accounts;
    private String currentUser;

    @BeforeEach
    void setUp() {
        savedPasswords = new HashMap<>();
        accounts = new HashMap<>();

        // Setup test account
        Account account = new Account();
        account.setEmail("test@example.com");
        account.setPassword("CurrentPass123!");
        accounts.put(account.getEmail(), account);

        // Setup test password
        Password password = new Password(1L, "example.com", "OldPass123!");
        savedPasswords.put("example.com", password);
    }

    @Test
    void testEditPassword() {
        // Check if account exists
        Account userAccount = accounts.get("test@example.com");
        Assertions.assertNotNull(userAccount, "Test account should exist");

        // Check if password exists
        Password savedPassword = savedPasswords.get("example.com");
        Assertions.assertNotNull(savedPassword, "Saved password should exist");

        // Update password
        savedPassword.setPassword("NewPass456!");
        savedPasswords.put("example.com", savedPassword);

        // Verify password was updated
        Password updatedPassword = savedPasswords.get("example.com");
        Assertions.assertEquals("NewPass456!", updatedPassword.getPassword(),
                "Password should be updated");
    }

    @Test
    void testViewPasswords() {
        // Verify we can retrieve passwords
        Password savedPassword = savedPasswords.get("example.com");
        Assertions.assertNotNull(savedPassword, "Should be able to retrieve saved password");
        Assertions.assertEquals("OldPass123!", savedPassword.getPassword(),
                "Should retrieve correct password");
    }
}