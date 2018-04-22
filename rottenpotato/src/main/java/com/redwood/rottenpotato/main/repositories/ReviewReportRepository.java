package com.redwood.rottenpotato.main.repositories;

import com.redwood.rottenpotato.main.models.UserReviewReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewReportRepository extends JpaRepository<UserReviewReport, Long> {
    long removeByReviewId(long id);
}
