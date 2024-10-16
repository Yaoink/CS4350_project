package edu.gsu.cs4350.Models;

import java.time.LocalDateTime;

public class Password {
    private int passwordId;
    private String email;
    private String websiteId;
    private String encryptedPassword;
    private LocalDateTime timeCreated;
    private LocalDateTime lastModified;
    private String username;

    public void setpasswordId(int passwordId) {
        this.passwordId = passwordId;
    }

    public int getpasswordId() {
        return passwordId;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getemail() {
        return email;
    }

    public void setwebsiteId(String websiteId) {
        this.websiteId = websiteId;
    }

    public String getwebsiteId() {
        return websiteId;
    }

    public void setencryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getencryptedPassword() {
        return encryptedPassword;
    }

    public void settimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public LocalDateTime gettimeCreated() {
        return timeCreated;
    }

    public void setlastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public LocalDateTime getlastModified() {
        return lastModified;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getusername() {
        return username;
    }
}
