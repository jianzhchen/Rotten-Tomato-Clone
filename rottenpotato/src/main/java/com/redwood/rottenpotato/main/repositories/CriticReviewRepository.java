package com.redwood.rottenpotato.main.repositories;

import com.redwood.rottenpotato.main.models.Actor;
import com.redwood.rottenpotato.main.models.CriticReview;
import com.redwood.rottenpotato.main.models.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriticReviewRepository extends JpaRepository<CriticReview, Long> {
    List<CriticReview> findByItemKey(String itemKey);

    List<CriticReview> findTop10ByOrderByReviewTimeDateDesc(Pageable pageable);

    List<CriticReview> findTop10ByCriticKeyOrderByReviewTimeDateDesc(String criticKey, Pageable pageable);

    @Query("select c,sum(c.criticKey)as num from CriticReview c group by c.criticKey order by num desc ")
    List<CriticReview> findTop10ByReviewCount();
}
