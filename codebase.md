# README.md

```md
# CS4350_project
```

# passwordgeneratorbackend\pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.6.3</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <groupId>edu.gsu.cs4350</groupId>
    <artifactId>passwordgeneratorbackend</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <java.version>17</java.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starter -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <!-- Spring Boot Starter Web (if you're building a web application) -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- Spring Boot Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- JUnit 5 -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- Mockito -->
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- AssertJ -->
        <dependency>
            <groupId>org.assertj</groupId>
            <artifactId>assertj-core</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

# .vscode\settings.json

```json
{
    "java.configuration.updateBuildConfiguration": "interactive"
}
```

# passwordgeneratorbackend\src\test\java\PasswordGenerationTestcases.java

```java

//for MVC testing
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/*methods to test
    - if user has account already and lets them know they already have an account when they try to signup 
    - if database successfully saves user account
    - if account is created in signup
    - if password is generated randomly based on specifications
        -lower case
        -upper case
        -symbols
    - if password length is met
    - if saved password database connects to the front end
    - if saved passwords displays
    - if forgot link is sent to email associated with account
    - if password is reset after user changes it
    - if webapp connects to browswer and loads the homepage
    - if user can edit a saved password
    - if a saved password updates after user edits it
    - if user can add a website and an existing password
    - if user can logout
*/

@SpringBootTest

public class PasswordGenerationTestcases {
    @Test
    public void accountCreation(database accounts, String email, String password) {
        // checks if account was successfully created
        // query database for specific account and select * from accounts where
        // accounts=email
        // then assert that it returns one row
        if (email == accounts.email && password == accounts.password)
            assertTrue(accounts);
    }

}

```

# passwordgeneratorbackend\src\main\java\edu\gsu\cs4350\PasswordGenerator.java

```java
package edu.gsu.cs4350;

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
            // If the user doesn't want to specify options, generate a random password of
            // length 8-12
            System.out
                    .println("No specifications selected. Generating a random password with length between 8 and 12.");
            int randomLength = getRandomLength(8, 12);
            password = generatePassword(randomLength, true, true, true);
        }

        // Display the generated password
        System.out.println("Generated Password: " + password);

        scanner.close();
    }

    // Method to generate a random password
    private static String generatePassword(int length, boolean includeLetters, boolean includeNumbers,
            boolean includeSymbols) {
        StringBuilder characterSet = new StringBuilder();
        Random random = new Random();

        if (includeLetters) {
            characterSet.append(LETTERS);
        }
        if (includeNumbers) {
            characterSet.append(NUMBERS);
        }
        if (includeSymbols) {
            characterSet.append(SYMBOLS);
        }

        // Generate the random password
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characterSet.length());
            password.append(characterSet.charAt(index));
        }

        return password.toString();
    }

    // Method to generate a random number between a given range (inclusive)
    private static int getRandomLength(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
```

# passwordgeneratorbackend\src\main\java\edu\gsu\cs4350\Main.java

```java
package edu.gsu.cs4350;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
```

# passwordgeneratorbackend\src\main\java\edu\gsu\cs4350\Websecurity\WebSecurityconfig.java

```java
package edu.gsu.cs4350.Websecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // Configure security settings, e.g., CORS, CSRF, authentication
}

```

# passwordgeneratorbackend\src\main\java\edu\gsu\cs4350\Websecurity\TokenProvider.java

```java
package edu.gsu.cs4350.Websecurity;

import org.springframework.stereotype.Component;

@Component
public class TokenProvider {
    public String generateToken(Authentication authentication);

    public String getUsernameFromToken(String token);

    public boolean validateToken(String token);
}

```

# passwordgeneratorbackend\src\main\java\edu\gsu\cs4350\Util\PasswordEncryptionUtil.java

```java
package edu.gsu.cs4350.Util;

public class PasswordEncryptionUtil {
    public static String encryptPassword(String plainTextPassword);

    public static boolean verifyPassword(String plainTextPassword, String encryptedPassword);
}
```

# passwordgeneratorbackend\src\main\java\edu\gsu\cs4350\Services\UserService.java

```java
package edu.gsu.cs4350.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.gsu.cs4350.Models.User;
import edu.gsu.cs4350.Repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(String email, String password);

    public User getUserByEmail(String email);

    public boolean authenticateUser(String email, String password);
    // Other user-related methods
}
```

# passwordgeneratorbackend\src\main\java\edu\gsu\cs4350\Services\PasswordService.java

```java
package edu.gsu.cs4350.Services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import edu.gsu.cs4350.Models.Password;
import edu.gsu.cs4350.Repositories.PasswordRepository;
import edu.gsu.cs4350.Repositories.WebsiteRepository;

@Service
public class PasswordService {
    @Autowired
    private PasswordRepository passwordRepository;
    @Autowired
    private WebsiteRepository websiteRepository;

    public Password savePassword(String email, String websiteUrl, String username, String password);

    public List<Password> getPasswordsForUser(String email);

    public Password updatePassword(int passwordId, String newPassword);

    public void deletePassword(int passwordId);
    // Other password-related methods
}
```

# passwordgeneratorbackend\src\main\java\edu\gsu\cs4350\Services\PasswordResetService.java

```java
package edu.gsu.cs4350.Services;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PasswordResetService {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    public void initiatePasswordReset(String email);

    public boolean validateResetToken(String token);

    public void resetPassword(String token, String newPassword);
}

```

# passwordgeneratorbackend\src\main\java\edu\gsu\cs4350\Services\PasswordGeneratorService.java

```java
package edu.gsu.cs4350.Services;

import org.springframework.stereotype.Service;

@Service
public class PasswordGeneratorService {
    public String generateRandomPassword(int length, boolean includeUppercase, boolean includeNumbers,
            boolean includeSymbols);
    // Other password generation methods
}

```

# passwordgeneratorbackend\src\main\java\edu\gsu\cs4350\Services\EmailService.java

```java
package edu.gsu.cs4350.Services;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendPasswordResetEmail(String email, String resetToken);
    // Other email-related methods
}

```

# passwordgeneratorbackend\src\main\java\edu\gsu\cs4350\Repositories\WebsiteRepository.java

```java
package edu.gsu.cs4350.Repositories;

public interface WebsiteRepository extends JpaRepository<Website, String> {
    Website findByUrl(String url);
}
```

# passwordgeneratorbackend\src\main\java\edu\gsu\cs4350\Repositories\UserRepository.java

```java
package edu.gsu.cs4350.Repositories;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
```

# passwordgeneratorbackend\src\main\java\edu\gsu\cs4350\Repositories\PasswordRepository.java

```java
package edu.gsu.cs4350.Repositories;

public interface PasswordRepository extends JpaRepository<Password, Integer> {
    List<Password> findByEmail(String email);

    Password findByEmailAndWebsiteId(String email, String websiteId);
}
```

# passwordgeneratorbackend\src\main\java\edu\gsu\cs4350\Models\Website.java

```java
package edu.gsu.cs4350.Models;

public class Website {
    private String websiteId;
    private String websiteName;
    private String url;
    // Getters and setters
}

```

# passwordgeneratorbackend\src\main\java\edu\gsu\cs4350\Models\User.java

```java
package edu.gsu.cs4350.Models;

public class User {
    private String email;
    private String encryptedAccountPassword;
    private LocalDateTime timeCreated;
    private LocalDateTime lastLogin;
    // Getters and setters
}
```

# passwordgeneratorbackend\src\main\java\edu\gsu\cs4350\Models\Password.java

```java
package edu.gsu.cs4350.Models;

public class Password {
    private int passwordId;
    private String email;
    private String websiteId;
    private String encryptedPassword;
    private LocalDateTime timeCreated;
    private LocalDateTime lastModified;
    private String username;
    // Getters and setters
}

```

# passwordgeneratorbackend\src\main\java\edu\gsu\cs4350\Controllers\UserController.java

```java
package edu.gsu.cs4350.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import edu.gsu.cs4350.Models.User;
import edu.gsu.cs4350.Services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody UserSignUpRequest request);

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest request);

}

```

# passwordgeneratorbackend\src\main\java\edu\gsu\cs4350\Controllers\PasswordGeneratorController.java

```java
package edu.gsu.cs4350.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.gsu.cs4350.Services.PasswordGeneratorService;

@RestController
@RequestMapping("/api/password-generator")
public class PasswordGeneratorController {
    @Autowired
    private PasswordGeneratorService passwordGeneratorService;

    @PostMapping
    public ResponseEntity<String> generatePassword(@RequestBody GeneratePasswordRequest request);
}

```

# passwordgeneratorbackend\src\main\java\edu\gsu\cs4350\Controllers\PasswordController.java

```java
package edu.gsu.cs4350.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/password-generator")
public class PasswordGeneratorController {
    @Autowired
    private PasswordGeneratorService passwordGeneratorService;

    @PostMapping
    public ResponseEntity<String> generatePassword(@RequestBody GeneratePasswordRequest request);
}

```

