package com.redwood.rottenpotato.security.repository;

import com.redwood.rottenpotato.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findById(long id);

    User findByToken(String token);
}
