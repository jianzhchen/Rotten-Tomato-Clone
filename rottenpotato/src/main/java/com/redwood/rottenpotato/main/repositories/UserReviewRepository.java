package com.redwood.rottenpotato.main.repositories;

import com.redwood.rottenpotato.main.models.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
    UserReview findById(long id);
    UserReview findByUserIdAndItemKey(long userId,String itemKey);
    List<UserReview> findByUserId(long userId);
    List<UserReview> findByItemKey(String itemKey);
    @Transactional
    long removeById(long id);

    @Transactional
    long removeByUserId(long userId);
}
