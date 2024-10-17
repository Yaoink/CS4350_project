package com.csc4350.passwordgen;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // Custom query method to find account by email
    Optional<Account> findByEmail(String email);
}
