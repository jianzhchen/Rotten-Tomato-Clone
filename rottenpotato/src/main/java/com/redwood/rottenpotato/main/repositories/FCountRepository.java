package com.redwood.rottenpotato.main.repositories;

import com.redwood.rottenpotato.main.models.FCount;
import com.redwood.rottenpotato.main.models.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FCountRepository extends JpaRepository<FCount, Long> {
    FCount findByUserId(long userId);
    @Transactional
    long removeByUserId(long userId);
}
