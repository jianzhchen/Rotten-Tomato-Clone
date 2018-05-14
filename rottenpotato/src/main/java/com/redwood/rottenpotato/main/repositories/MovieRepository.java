package com.redwood.rottenpotato.main.repositories;

import com.redwood.rottenpotato.main.models.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m WHERE m.name LIKE %:searchTerm%")
    public List<Movie> searchByName(@Param("searchTerm") String searchTerm,Pageable pageable);

    public List<Movie> findTop10ByOrderByBoxOfficeDesc(Pageable pageable);
    public List<Movie> findTop8ByOrderByBoxOfficeDesc(Pageable pageable);

    @Query("select m from Movie m where m.inTheatersTime>=CURRENT_DATE order by m.inTheatersTime ASC ")
    public List<Movie> findComingSoon( Pageable pageable);

    public Movie findByMovieKey(String movieKey);

    long removeByMovieKey(String movieKey);

    @Query("SELECT m FROM Movie m WHERE m.cast LIKE %:actorKey%")
    public List<Movie> searchByActorKey(@Param("actorKey") String actorKey);

    public List<Movie> findAll();

    @Query("select m from Movie m Where m.oscarBestPictureYear <>0 order by m.oscarBestPictureYear desc ")
    public List<Movie> findOscarWinningYearDesc(Pageable pageable);

    @Query("SELECT m FROM Movie m WHERE m.director LIKE %:searchTerm%")
    public List<Movie> searchByDirector(@Param("searchTerm") String searchTerm,Pageable pageable);
}
