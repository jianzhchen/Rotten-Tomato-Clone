package com.redwood.rottenpotato.main.repositories;

import com.redwood.rottenpotato.main.models.Actor;
import com.redwood.rottenpotato.main.models.Follow;
import com.redwood.rottenpotato.main.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    Actor findByActorKey(String actorKey);

    @Query("SELECT a FROM Actor a WHERE a.actorName LIKE %:searchTerm%")
    public List<Actor> searchByName(@Param("searchTerm") String searchTerm);

}
