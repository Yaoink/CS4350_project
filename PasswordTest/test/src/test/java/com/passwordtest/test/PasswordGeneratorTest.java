package com.passwordtest.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordGeneratorTest {
    
    @Test
    void testAlwaysPasses(){
        assertTrue(true);
    }
    @Test
    void testPasswordWithLettersOnly(){
        String password = PasswordGenerator.generatePassword(10, true, false, false);
        System.out.println("Generated password: " + password);
        assertTrue(password.matches("[A-Za-z]{10}"));
    }

    @Test
    void testPasswordWithNumbersOnly(){
        String password = PasswordGenerator.generatePassword(10, false, true, false);
        System.out.println("Generated password: " + password);
        assertTrue(password.matches("[0-9]{10}"));
    }

    @Test
    void testPasswordWithNumberAndLetter(){
        String password = PasswordGenerator.generatePassword(10, true, true, false);
        System.out.println("Generated password: " + password);
        assertTrue(password.matches("[A-Za-z0-9]{10}"));
    }
}
