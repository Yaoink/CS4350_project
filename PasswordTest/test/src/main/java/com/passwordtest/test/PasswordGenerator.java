package com.passwordtest.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {

    
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user if they want to choose specifications
        System.out.print("Would you like to specify the password options? (yes/no): ");
        boolean specifyOptions = scanner.next().equalsIgnoreCase("yes");

        String password;
        if (specifyOptions) {
            // If the user wants to specify options
            System.out.print("Enter the desired password length: ");
            int length = scanner.nextInt();

            System.out.print("Include letters? (yes/no): ");
            boolean includeLetters = scanner.next().equalsIgnoreCase("yes");

            System.out.print("Include numbers? (yes/no): ");
            boolean includeNumbers = scanner.next().equalsIgnoreCase("yes");

            System.out.print("Include symbols? (yes/no): ");
            boolean includeSymbols = scanner.next().equalsIgnoreCase("yes");

            // If no preferences are selected, default to all character sets
            if (!includeLetters && !includeNumbers && !includeSymbols) {
                System.out.println("No specifications selected. Using all character sets.");
                includeLetters = true;
                includeNumbers = true;
                includeSymbols = true;
            }

            // Generate password based on user's specifications
            password = generatePassword(length, includeLetters, includeNumbers, includeSymbols);
        } else {
            // If the user doesn't want to specify options, generate a random password of length 8-12
            System.out.println("No specifications selected. Generating a random password with length between 8 and 12.");
            int randomLength = getRandomLength(8, 12);
            password = generatePassword(randomLength, true, true, true);
        }

        // Display the generated password
        System.out.println("Generated Password: " + password);
        
        scanner.close();
    }

    // Method to generate a random password
    public static String generatePassword(int length, boolean includeLetters, boolean includeNumbers, boolean includeSymbols) {
        StringBuilder characterSet = new StringBuilder();
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        int remainingLength = length;

        if (includeLetters) {
            characterSet.append(LETTERS);
            password.append(LETTERS.charAt(random.nextInt(LETTERS.length())));
            remainingLength--;
        }
        if (includeNumbers) {
            characterSet.append(NUMBERS);
            password.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
            remainingLength--;
        }
        if (includeSymbols) {
            characterSet.append(SYMBOLS);
            password.append(SYMBOLS.charAt(random.nextInt(SYMBOLS.length())));
            remainingLength--;
        }
        // System.out.println("Character Set: " + characterSet.toString());
        
        
        
        for (int i = 0; i < remainingLength; i++) {
            int index = random.nextInt(characterSet.length());
            password.append(characterSet.charAt(index));
        }
        
        return password.toString();
        // return shuffleString(password.toString());
    }

    // Method to generate a random number between a given range (inclusive)
    public static int getRandomLength(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static String shuffleString(String input) {
        List<Character> characters = new ArrayList<>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }
        Collections.shuffle(characters);
        StringBuilder shuffled = new StringBuilder();
        for (char c : characters) {
            shuffled.append(c);
        }
        return shuffled.toString();
    }
}

