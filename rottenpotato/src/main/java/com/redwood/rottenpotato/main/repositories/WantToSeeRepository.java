package com.redwood.rottenpotato.main.repositories;

import com.redwood.rottenpotato.main.models.Review;
import com.redwood.rottenpotato.main.models.WantToSee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WantToSeeRepository extends JpaRepository<WantToSee, Long> {
    long removeByMovieKeyAndUserId(String movieKey, long userId);
}
