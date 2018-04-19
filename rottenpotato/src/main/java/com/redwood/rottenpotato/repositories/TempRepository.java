package com.redwood.rottenpotato.repositories;

import com.redwood.rottenpotato.models.Temp;
import com.redwood.rottenpotato.security.model.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TempRepository extends JpaRepository<Temp, Long> {
    @Query("SELECT t FROM Temp t WHERE t.name LIKE %:searchTerm%")
    public List<Temp> searchByName(@Param("searchTerm") String searchTerm);

    public List<Temp> findTop10ByOrderByBoxOfficeDesc(Pageable pageable);

    public List<Temp> findByStudio(String studio);
}
