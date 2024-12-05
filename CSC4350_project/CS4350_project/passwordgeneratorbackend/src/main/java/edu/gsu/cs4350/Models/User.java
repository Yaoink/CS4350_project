package edu.gsu.cs4350.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String encryptedAccountPassword;
    private LocalDateTime timeCreated;
    private LocalDateTime lastLogin;
    @Column(length = 6)
    private String resetToken;
    private LocalDateTime resetTokenExpiration;
    private String testString;

    public void setemail(String email) {
        this.email = email;
    }

    public String getemail() {
        return email;
    }

    public void setencryptedAccountPassword(String encryptedAccountPassword) {
        this.encryptedAccountPassword = encryptedAccountPassword;
    }

    public String getencryptedAccountPassword() {
        return encryptedAccountPassword;
    }

    public void settimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public LocalDateTime gettimeCreated() {
        return timeCreated;
    }

    public void setlastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public LocalDateTime getlastLogin() {
        return lastLogin;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetTokenExpiration(LocalDateTime resetTokenExpiration) {
        this.resetTokenExpiration = resetTokenExpiration;
    }

    public LocalDateTime getResetTokenExpiration() {
        return resetTokenExpiration;
    }

    public boolean isResetTokenValid() {
        return resetToken != null &&
                resetTokenExpiration != null &&
                LocalDateTime.now().isBefore(resetTokenExpiration);
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }

    public String getTestString() {
        return testString;
    }
}