package com.redwood.rottenpotato.main.repositories;

import com.redwood.rottenpotato.main.models.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRatingRepository extends JpaRepository<UserRating, Long> {
    UserRating findByItemKeyAndUserId(String itemKey, long userId);
    UserRating findById(long Id);
    List<UserRating> findByItemKey(String itemKey);
    List<UserRating> findByUserId(long userId);

    long removeById(long id);

    long removeByUserId(long userId);
}
