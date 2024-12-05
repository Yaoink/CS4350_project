package edu.gsu.cs4350.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.gsu.cs4350.Services.PasswordResetService;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/password-reset")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/request")
    public ResponseEntity<?> requestReset(@RequestBody Map<String, String> request) {
        try {
            String email = request.get("email");
            if (email == null || email.trim().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of(
                        "success", false,
                        "message", "Email is required"));
            }

            passwordResetService.initiatePasswordReset(email);

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Reset email sent successfully",
                    "data", Map.of("email", email)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Failed to initiate password reset"));
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyToken(@RequestBody Map<String, String> request) {
        try {
            String email = request.get("email");
            String token = request.get("token");

            if (email == null || token == null) {
                return ResponseEntity.badRequest().body(Map.of(
                        "success", false,
                        "message", "Email and token are required"));
            }

            boolean isValid = passwordResetService.validateResetToken(email, token);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", isValid ? "Token is valid" : "Token is invalid");
            response.put("data", Map.of("valid", isValid));

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()));
        }
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        try {
            String email = request.get("email");
            String token = request.get("token");
            String newPassword = request.get("newPassword");

            // Validate required fields
            if (email == null || token == null || newPassword == null) {
                return ResponseEntity.badRequest().body(Map.of(
                        "success", false,
                        "message", "Email, token, and new password are required"));
            }

            passwordResetService.resetPassword(email, token, newPassword);

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Password reset successful",
                    "data", Map.of("email", email)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Failed to reset password"));
        }
    }
}