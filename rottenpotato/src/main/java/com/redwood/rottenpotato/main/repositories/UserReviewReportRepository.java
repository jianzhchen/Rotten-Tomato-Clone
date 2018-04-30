package com.redwood.rottenpotato.main.repositories;

import com.redwood.rottenpotato.main.models.UserReviewReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface UserReviewReportRepository extends JpaRepository<UserReviewReport, Long> {
    long removeByReviewId(long id);

    List<UserReviewReport> findTop10ByOrderById(Pageable pageable);
}
