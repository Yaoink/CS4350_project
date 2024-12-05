package edu.gsu.cs4350.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.gsu.cs4350.Services.JwtService;
import edu.gsu.cs4350.Services.UserService;
import edu.gsu.cs4350.Models.User;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody Map<String, String> request) {
        try {
            String email = request.get("email");
            String password = request.get("password");
            System.out.println("Signup attempt for email: " + email);

            userService.createUser(email, password);
            String token = jwtService.generateToken(email);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Signup successful");
            response.put("data", Map.of(
                    "email", email,
                    "token", token));

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("Error in signup: " + e);
            e.printStackTrace();
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        try {
            String email = request.get("email");
            String password = request.get("password");

            if (email == null || password == null) {
                return ResponseEntity.badRequest().body(Map.of(
                        "success", false,
                        "message", "Email and password are required"));
            }

            boolean isAuthenticated = userService.authenticateUser(email, password);

            if (isAuthenticated) {
                String token = jwtService.generateToken(email);

                return ResponseEntity.ok(Map.of(
                        "success", true,
                        "message", "Login successful",
                        "data", Map.of(
                                "email", email,
                                "token", token)));
            } else {
                return ResponseEntity.badRequest().body(Map.of(
                        "success", false,
                        "message", "Invalid credentials"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()));
        }
    }

    @PostMapping("/verify-token")
    public ResponseEntity<?> verifyToken(@RequestHeader("Authorization") String authHeader) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.badRequest().body(Map.of(
                        "success", false,
                        "message", "Invalid token format"));
            }

            String token = authHeader.substring(7);
            boolean isValid = jwtService.isTokenValid(token);

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Token verification completed",
                    "data", Map.of("valid", isValid)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()));
        }
    }

    @PostMapping("/set-test-string")
    public ResponseEntity<?> setTestString(@RequestBody Map<String, String> request) {
        try {
            String email = request.get("email");
            String testString = request.get("testString");

            if (email == null || testString == null) {
                return ResponseEntity.badRequest().body(Map.of(
                        "success", false,
                        "message", "Email and test string are required"));
            }

            userService.setTestString(email, testString);

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Test string set successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()));
        }
    }

    @GetMapping("/get-test-string")
    public ResponseEntity<?> getTestString(@RequestParam String email) {
        try {
            User user = userService.getUserByEmail(email);
            if (user == null) {
                return ResponseEntity.badRequest().body(Map.of(
                        "success", false,
                        "message", "User not found"));
            }

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Test string retrieved successfully",
                    "data", Map.of("testString", user.getTestString())));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Logged out successfully"));
    }

    @GetMapping("/check-email")
    public ResponseEntity<?> checkEmailAvailability(@RequestParam String email) {
        try {
            User existingUser = userService.getUserByEmail(email);
            boolean isAvailable = existingUser == null;

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Email availability checked",
                    "data", Map.of("available", isAvailable)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()));
        }
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Test endpoint working"));
    }
}
