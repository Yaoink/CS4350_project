package edu.gsu.cs4350.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.gsu.cs4350.Models.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // find user by email (this is our main lookup method)
    Optional<User> findByEmail(String email);

    // check if email exists (useful for registration)
    boolean existsByEmail(String email);
}