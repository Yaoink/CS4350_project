package edu.gsu.cs4350.Websecurity;

import org.springframework.stereotype.Component;

@Component
public class TokenProvider {
    public String generateToken(Authentication authentication);

    public String getUsernameFromToken(String token);

    public boolean validateToken(String token);
}
