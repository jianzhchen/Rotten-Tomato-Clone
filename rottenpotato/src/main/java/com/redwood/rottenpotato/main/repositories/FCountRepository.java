package com.redwood.rottenpotato.main.repositories;

import com.redwood.rottenpotato.main.models.FCount;
import com.redwood.rottenpotato.main.models.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FCountRepository extends JpaRepository<FCount, Long> {
    FCount findByUserId(long userId);
    long removeByUserId(long userId);
}
