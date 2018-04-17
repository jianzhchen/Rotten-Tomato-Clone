package com.redwood.rottenpotato.repositories;

import com.redwood.rottenpotato.models.Movie;
import org.springframework.data.repository.CrudRepository;


public interface MovieRepository extends CrudRepository<Movie, Long>
{
    @Override
    Iterable<Movie> findAll();
}
