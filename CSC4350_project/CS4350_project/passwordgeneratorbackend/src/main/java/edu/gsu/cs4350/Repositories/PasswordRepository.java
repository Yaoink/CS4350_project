package edu.gsu.cs4350.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.gsu.cs4350.Models.Password;
import java.util.List;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Integer> {
        // Only get all passwords for a user
        List<Password> findByEmail(String email);
}
