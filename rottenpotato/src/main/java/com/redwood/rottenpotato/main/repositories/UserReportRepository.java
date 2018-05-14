package com.redwood.rottenpotato.main.repositories;

import com.redwood.rottenpotato.main.models.UserReport;
import com.redwood.rottenpotato.main.models.UserReviewReport;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserReportRepository extends JpaRepository<UserReport, Long> {
}
