package com.redwood.rottenpotato.repositories;

import com.redwood.rottenpotato.models.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;


public interface MovieRepository extends CrudRepository<Movie, Long>
{
    Iterable<Movie> findAllByMovieDateBetween(Date sunday,Date saturday );
}

