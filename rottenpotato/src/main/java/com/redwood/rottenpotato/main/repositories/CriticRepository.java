package com.redwood.rottenpotato.main.repositories;

import com.redwood.rottenpotato.main.models.Critic;
import com.redwood.rottenpotato.main.models.CriticReview;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriticRepository extends JpaRepository<Critic, Long> {
    Critic findByCriticKey(String criticKey);

    List<Critic> findAll();

    List<Critic> findTop10ByOrderByCriticNameDesc(Pageable pageable);
}
