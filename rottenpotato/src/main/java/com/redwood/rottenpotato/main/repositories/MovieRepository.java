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
    public List<Movie> searchByName(@Param("searchTerm") String searchTerm,Pageable pageable);

    public List<Movie> findTop10ByOrderByBoxOfficeDesc(Pageable pageable);
    public List<Movie> findTop8ByOrderByBoxOfficeDesc(Pageable pageable);

    @Query("SELECT m FROM Movie m order by m.inTheatersTime DESC")
    public List<Movie> findTop10ByOrderByInTheatersTimeDesc(Pageable pageable);
    public List<Movie> findTop8ByOrderByInTheatersTimeDesc(Pageable pageable);

    public Movie findByMovieKey(String movieKey);

    long removeByMovieKey(String movieKey);
}
