package edu.gsu.cs4350;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "edu.gsu.cs4350" })
public class PasswordManager {
    public static void main(String[] args) {
        SpringApplication.run(PasswordManager.class, args);
    }
}