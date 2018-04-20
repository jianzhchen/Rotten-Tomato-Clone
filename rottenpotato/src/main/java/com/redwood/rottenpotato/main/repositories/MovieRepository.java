package com.redwood.rottenpotato.main.repositories;

import com.redwood.rottenpotato.main.models.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m WHERE m.name LIKE %:searchTerm%")
    public List<Movie> searchByName(@Param("searchTerm") String searchTerm);

    public List<Movie> findTop10ByOrderByBoxOfficeDesc(Pageable pageable);

    @Query("SELECT m FROM Movie m order by m.inTheatersDate DESC")
    public List<Movie> findTop10ByOrderByInTheatersDateDesc(Pageable pageable);

    public Movie findByMovieKey(String movieKey);
}
