package edu.gsu.cs4350.Models;

import java.time.LocalDateTime;

public class User {
    private String email;
    private String encryptedAccountPassword;
    private LocalDateTime timeCreated;
    private LocalDateTime lastLogin;
    private String resetToken;

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
}