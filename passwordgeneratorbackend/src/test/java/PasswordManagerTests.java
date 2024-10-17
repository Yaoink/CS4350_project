import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import edu.gsu.cs4350.Services.*;
import edu.gsu.cs4350.Models.*;
import edu.gsu.cs4350.Repositories.*;

class PasswordManagerTests {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordRepository passwordRepository;
    @Mock
    private WebsiteRepository websiteRepository;
    @Mock
    private EmailService emailService;

    @InjectMocks
    private UserService userService;
    @InjectMocks
    private PasswordService passwordService;
    @InjectMocks
    private PasswordGeneratorService passwordGeneratorService;
    @InjectMocks
    private PasswordResetService passwordResetService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Scenario 1: Guest Password Generation
    @Test
    void testGenerateRandomPassword() {
        String password = passwordGeneratorService.generateRandomPassword(12, true, true, true);
        assertNotNull(password);
        assertEquals(12, password.length());
    }

    @Test
    void testGeneratePasswordWithoutNumbers() {
        String password = passwordGeneratorService.generateRandomPassword(10, true, false, true);
        assertNotNull(password);
        assertTrue(password.matches("^[a-zA-Z!@#$%^&*()-_=+\\[\\]{}|;:'\",.<>?/]+$"));
    }

    @Test
    void testGeneratePasswordOnlyLetters() {
        String password = passwordGeneratorService.generateRandomPassword(8, true, false, false);
        assertNotNull(password);
        assertTrue(password.matches("^[a-zA-Z]+$"));
    }

    // Scenario 2: New User Account Creation and Password Save
    @Test
    void testCreateNewUser() {
        String email = "test@example.com";
        String password = "securePassword123";
        when(userRepository.findByEmail(email)).thenReturn(null);

        User newUser = userService.createUser(email, password);

        assertNotNull(newUser);
        assertEquals(email, newUser.getemail());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testCreateDuplicateUser() {
        String email = "existing@example.com";
        String password = "password123";
        when(userRepository.findByEmail(email)).thenReturn(new User());

        assertThrows(IllegalArgumentException.class, () -> userService.createUser(email, password));
    }

    @Test
    void testSaveGeneratedPassword() {
        String email = "user@example.com";
        String websiteUrl = "https://example.com";
        String username = "testuser";
        String password = "generatedPassword123";

        when(websiteRepository.findByUrl(websiteUrl)).thenReturn(null);

        Password savedPassword = passwordService.savePassword(email, websiteUrl, username, password);

        assertNotNull(savedPassword);
        assertEquals(email, savedPassword.getemail());
        assertEquals(username, savedPassword.getusername());
        verify(passwordRepository).save(any(Password.class));
    }

    // Scenario 3: Password Editing
    @Test
    void testUpdateExistingPassword() {
        int passwordId = 1;
        String newPassword = "updatedPassword123";
        Password existingPassword = new Password();
        existingPassword.setpasswordId(passwordId);

        when(passwordRepository.findById(passwordId)).thenReturn(java.util.Optional.of(existingPassword));

        Password updatedPassword = passwordService.updatePassword(passwordId, newPassword);

        assertNotNull(updatedPassword);
        assertEquals(newPassword, updatedPassword.getencryptedPassword());
        verify(passwordRepository).save(any(Password.class));
    }

    @Test
    void testUpdateNonExistentPassword() {
        int passwordId = 999;
        String newPassword = "updatedPassword123";

        when(passwordRepository.findById(passwordId)).thenReturn(java.util.Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> passwordService.updatePassword(passwordId, newPassword));
    }

    @Test
    void testGetPasswordsForUser() {
        String email = "user@example.com";
        List<Password> passwords = Arrays.asList(new Password(), new Password());

        when(passwordRepository.findByEmail(email)).thenReturn(passwords);

        List<Password> retrievedPasswords = passwordService.getPasswordsForUser(email);

        assertNotNull(retrievedPasswords);
        assertEquals(2, retrievedPasswords.size());
    }

    // Scenario 4: Password Reset Process
    @Test
    void testInitiatePasswordReset() {
        String email = "user@example.com";
        User user = new User();
        user.setemail(email);

        when(userRepository.findByEmail(email)).thenReturn(user);

        passwordResetService.initiatePasswordReset(email);

        verify(emailService).sendPasswordResetEmail(eq(email), anyString());
    }

    @Test
    void testValidateResetToken() {
        String token = "validToken123";
        User user = new User();

        when(userRepository.findByResetToken(token)).thenReturn(Optional.of(user));

        assertTrue(passwordResetService.validateResetToken(token));
    }

    @Test
    void testResetPassword() {
        String token = "validToken123";
        String newPassword = "newSecurePassword123";
        User user = new User();

        when(userRepository.findByResetToken(token)).thenReturn(Optional.of(user));

        passwordResetService.resetPassword(token, newPassword);

        verify(userRepository).save(any(User.class));
        assertNull(user.getResetToken());
    }
}
