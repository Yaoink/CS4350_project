package edu.gsu.cs4350.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.gsu.cs4350.Models.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);

    default Optional<User> findByResetToken(String token) {
        return Optional.empty();
    }
}