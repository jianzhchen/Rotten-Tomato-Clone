package com.redwood.rottenpotato.main.repositories;

import com.redwood.rottenpotato.main.models.Rating;
import com.redwood.rottenpotato.main.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Rating findByMovieKeyAndUserId(String movieKey, long userId);
    long removeById(long id);
    long removeByUserId(long userId);
}
