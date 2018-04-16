package com.redwood.rottenpotato.admin.repositories;

import com.redwood.rottenpotato.admin.models.CriticAppReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CriticAppReviewRepository extends JpaRepository<CriticAppReview, Long> {
    @Query("SELECT min(cr.id) FROM CriticAppReview cr")
    CriticAppReview deQueue();
}
