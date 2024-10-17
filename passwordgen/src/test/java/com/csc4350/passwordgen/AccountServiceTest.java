package com.csc4350.passwordgen;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAccountByEmail_Found() {
        // Arrange: Mock the repository to return an account for the given email
        String email = "test@example.com";
        Account account = new Account();
        account.setEmail(email);
        account.setPassword("password123");

        when(accountRepository.findByEmail(email)).thenReturn(Optional.of(account));

        // Act: Call the service method
        Optional<Account> result = accountService.getAccountByEmail(email);

        // Assert: Verify that the account was found
        assertTrue(result.isPresent());
        assertTrue(result.get().getEmail().equals(email));
    }

    @Test
    public void testGetAccountByEmail_NotFound() {
        // Arrange: Mock the repository to return an empty Optional
        String email = "notfound@example.com";
        when(accountRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act: Call the service method
        Optional<Account> result = accountService.getAccountByEmail(email);

        // Assert: Verify that no account was found
        assertTrue(result.isEmpty());
    }
}
