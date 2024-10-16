package edu.gsu.cs4350.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/password-generator")
public class PasswordGeneratorController {
    @Autowired
    private PasswordGeneratorService passwordGeneratorService;

    @PostMapping
    public ResponseEntity<String> generatePassword(@RequestBody GeneratePasswordRequest request);
}
