package com.csc4350.passwordgen;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Endpoint to get account by email
    @GetMapping("/email")
    public Optional<Account> getAccountByEmail(@RequestParam String email) {
        return accountService.getAccountByEmail(email);
    }
}
