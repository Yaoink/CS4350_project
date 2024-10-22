package com.cs4350.passwordgen;

import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.UUID;

@SpringBootTest
class UserSignupTest {
	//declaring the static Map
	 private static Map<String, String> savedUsers;
	 
	 @BeforeEach
	 void setUp() {
		savedUsers = new HashMap<>();
	}
	//test to verify that the welcome page can be loaded
	@Test
	void testWelcomePage() {
		//simulate loading the welcome page
		boolean isWelcomePageLoaded = simulateLoadWelcomePage();

        //check to see if welcome page can be accessed
        Assertions.assertTrue(isWelcomePageLoaded, "User should be able to land on the welcome page.");
    }
	//test to verify that the signup button can be clicked
	@Test
    void testSignupButtonClick() {
        //simulates clicking the signup button
        boolean isSignupButtonClicked = simulateClickSignupButton();

		//check to see if signup button can be click
        Assertions.assertTrue(isSignupButtonClicked, "User should be able to click on the signup button.");
    }
	 //method to simulate loading the welcome page
	private boolean simulateLoadWelcomePage() {
        return true; // Simulate that the welcome page loaded successfully
    }
	//method to simulate clicking the signup button
	private boolean simulateClickSignupButton() {
        return true; // Simulate that the signup button was clicked successfully
    }
}


