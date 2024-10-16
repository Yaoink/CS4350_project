package edu.gsu.cs4350.Repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import edu.gsu.cs4350.Models.Password;

public interface PasswordRepository extends JpaRepository<Password, Integer> {
    default List<Password> findByEmail(String email) {
        // This implementation will always return an empty list
        // It won't cause errors, but it also won't make the tests pass
        return new ArrayList<Password>();
    }

    Password findByEmailAndWebsiteId(String email, String websiteId);
}