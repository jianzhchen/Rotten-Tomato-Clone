package com.redwood.rottenpotato.repositories;

import com.redwood.rottenpotato.models.Item;
import com.redwood.rottenpotato.models.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long>
{
}
