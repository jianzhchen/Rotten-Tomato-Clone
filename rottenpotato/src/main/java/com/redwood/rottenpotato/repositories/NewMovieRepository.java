package com.redwood.rottenpotato.repositories;

import com.redwood.rottenpotato.models.NewMovie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewMovieRepository extends JpaRepository<NewMovie, Long> {
    @Query("SELECT m FROM NewMovie m WHERE m.name LIKE %:searchTerm%")
    public List<NewMovie> searchByName(@Param("searchTerm") String searchTerm);

    public List<NewMovie> findTop10ByOrderByBoxOfficeDesc(Pageable pageable);

}
