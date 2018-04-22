package com.redwood.rottenpotato.main.repositories;

import com.redwood.rottenpotato.main.models.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<UserRating, Long> {
    UserRating findByMovieKeyAndUserId(String movieKey, long userId);
    long removeById(long id);
    long removeByUserId(long userId);
}
