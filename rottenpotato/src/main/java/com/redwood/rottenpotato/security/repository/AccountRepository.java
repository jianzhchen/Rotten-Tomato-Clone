package com.redwood.rottenpotato.security.repository;

import com.redwood.rottenpotato.security.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String email);

    Account findByUsername(String username);
}
