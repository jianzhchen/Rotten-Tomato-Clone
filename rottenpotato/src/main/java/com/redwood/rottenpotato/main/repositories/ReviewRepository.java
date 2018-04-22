package com.redwood.rottenpotato.main.repositories;

import com.redwood.rottenpotato.main.models.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<UserReview, Long> {
    UserReview findById(long id);
    long removeById(long id);
    long removeByUserId(long userId);
}
