
//for MVC testing
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

public class TestCases {
    @test
    public void accountCreation(database accounts, String email, String password) {
        // checks if account was successfully created
        // query database for specific account and select * from accounts where
        // accounts=email
        // then assert that it returns one row
        if (email == accounts.email && password == accounts.password)
            assertTrue(accounts);
    }

}
