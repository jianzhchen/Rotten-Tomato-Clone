package com.redwood.rottenpotato.main.repositories;

import com.redwood.rottenpotato.main.models.Critic;
import com.redwood.rottenpotato.main.models.CriticApplication;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriticApplicationRepository extends JpaRepository<CriticApplication, Long> {

    CriticApplication findByUserId(long userId);

    List<CriticApplication> findAllBy();
}
