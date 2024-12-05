package edu.gsu.cs4350.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import edu.gsu.cs4350.Services.PasswordService;
import edu.gsu.cs4350.Models.Password;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/passwords")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboardPasswords() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = auth.getName();

            List<Password> passwords = passwordService.getPasswordsForUser(userEmail);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Passwords retrieved successfully");
            response.put("data", passwords);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()));
        }
    }

    @PutMapping("/{passwordId}")
    public ResponseEntity<?> updatePassword(@PathVariable int passwordId,
            @RequestBody Map<String, String> request) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = auth.getName();

            Password password = passwordService.getPasswordById(passwordId);
            if (!password.getemail().equals(userEmail)) {
                return ResponseEntity.badRequest().body(Map.of(
                        "success", false,
                        "message", "Unauthorized access - password belongs to different user"));
            }

            Password updated = passwordService.updatePassword(
                    passwordId,
                    request.get("newPassword"),
                    request.get("websiteUrl"),
                    request.get("websiteName"),
                    request.get("username"));

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Password updated successfully");
            response.put("data", updated);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()));
        }
    }

    @GetMapping("/{passwordId}")
    public ResponseEntity<?> getPasswordById(@PathVariable int passwordId) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = auth.getName();

            Password password = passwordService.getPasswordById(passwordId);
            if (!password.getemail().equals(userEmail)) {
                return ResponseEntity.badRequest().body(Map.of(
                        "success", false,
                        "message", "Unauthorized access - password belongs to different user"));
            }

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Password retrieved successfully");
            response.put("data", password);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> savePassword(@RequestBody Map<String, String> request) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = auth.getName();

            Password saved = passwordService.savePassword(
                    userEmail,
                    request.get("websiteUrl"),
                    request.get("websiteName"),
                    request.get("username"),
                    request.get("password"));

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Password saved successfully");
            response.put("data", saved);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()));
        }
    }

    @DeleteMapping("/{passwordId}")
    public ResponseEntity<?> deletePassword(@PathVariable int passwordId) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = auth.getName();

            Password password = passwordService.getPasswordById(passwordId);
            if (!password.getemail().equals(userEmail)) {
                return ResponseEntity.badRequest().body(Map.of(
                        "success", false,
                        "message", "Unauthorized access - password belongs to different user"));
            }

            passwordService.deletePassword(passwordId);

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Password deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()));
        }
    }
}
