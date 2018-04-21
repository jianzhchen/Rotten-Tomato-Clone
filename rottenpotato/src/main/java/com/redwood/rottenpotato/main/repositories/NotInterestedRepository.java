package com.redwood.rottenpotato.main.repositories;

import com.redwood.rottenpotato.main.models.NotInterested;
import com.redwood.rottenpotato.main.models.WantToSee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotInterestedRepository extends JpaRepository<NotInterested, Long> {
    long removeByMovieKeyAndUserId(String movieKey, long userId);
    long removeByUserId(long userId);
}
