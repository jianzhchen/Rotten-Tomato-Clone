package com.redwood.rottenpotato.main.repositories;

import com.redwood.rottenpotato.main.models.Follow;
import com.redwood.rottenpotato.main.models.WantToSee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    long removeByUserIdFromAndUserIdTo(long userIdFrom, long userIdTo);
}
