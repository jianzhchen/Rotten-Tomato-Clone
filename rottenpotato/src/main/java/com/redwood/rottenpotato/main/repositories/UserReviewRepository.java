package com.redwood.rottenpotato.main.repositories;

import com.redwood.rottenpotato.main.models.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
    UserReview findById(long id);
    List<UserReview> findByUserId(long userId);
    List<UserReview> findByItemKey(String itemKey);
    long removeById(long id);
    long removeByUserId(long userId);
}
