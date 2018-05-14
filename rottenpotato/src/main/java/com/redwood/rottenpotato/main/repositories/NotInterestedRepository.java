package com.redwood.rottenpotato.main.repositories;

import com.redwood.rottenpotato.main.models.NotInterested;
import com.redwood.rottenpotato.main.models.WantToSee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotInterestedRepository extends JpaRepository<NotInterested, Long> {
    long removeByItemKeyAndUserId(String itemKey, long userId);
    long removeByUserId(long userId);
    List<NotInterested> findByUserId( long userId);
    NotInterested findByItemKeyAndUserId(String itemKey, long userId);
}
