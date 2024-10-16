package edu.gsu.cs4350.Repositories;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}