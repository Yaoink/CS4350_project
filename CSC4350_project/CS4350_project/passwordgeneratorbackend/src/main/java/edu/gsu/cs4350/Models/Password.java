package edu.gsu.cs4350.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Password {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int passwordId;
    @Column(nullable = false)
    private String email;
    private String websiteName;
    private String url;
    @Column(nullable = false)
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

    public void setwebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public String getwebsiteName() {
        return websiteName;
    }

    public void seturl(String url) {
        this.url = url;
    }

    public String geturl() {
        return url;
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
