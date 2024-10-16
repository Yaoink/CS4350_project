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