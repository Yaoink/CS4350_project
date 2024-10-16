package edu.gsu.cs4350.Repositories;

public interface PasswordRepository extends JpaRepository<Password, Integer> {
    List<Password> findByEmail(String email);

    Password findByEmailAndWebsiteId(String email, String websiteId);
}