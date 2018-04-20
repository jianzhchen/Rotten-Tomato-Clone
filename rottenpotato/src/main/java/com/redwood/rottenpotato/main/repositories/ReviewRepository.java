package com.redwood.rottenpotato.main.repositories;

import com.redwood.rottenpotato.main.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findById(long id);

    long removeById(long id);
}
