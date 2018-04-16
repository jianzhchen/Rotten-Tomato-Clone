package com.redwood.rottenpotato.security.repository;

import com.redwood.rottenpotato.security.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
