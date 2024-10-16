package edu.gsu.cs4350.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<User> signUp(@RequestBody UserSignUpRequest request);

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest request);

}
