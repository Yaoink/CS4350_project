package com.csc4350.passwordgen;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {

    // Custom query method to find passwords by user ID
    List<Password> findByUserId(Long userId);
}
