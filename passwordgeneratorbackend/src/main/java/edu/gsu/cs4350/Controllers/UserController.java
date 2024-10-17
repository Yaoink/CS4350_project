package edu.gsu.cs4350.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import edu.gsu.cs4350.Models.User;
import edu.gsu.cs4350.Services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody Map<String, String> requestBody) {
        // This will compile, but always return null, causing tests to fail
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> requestBody) {
        // This will compile, but always return null, causing tests to fail
        return null;
    }

}
